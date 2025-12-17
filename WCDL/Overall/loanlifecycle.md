# Loan Life Cycle (High-Level View)

Understanding the loan life cycle is mandatory **before writing any calculation logic**.
Calculations only make sense when the business flow and states are clear.

---

## Loan Flow

Customer  
→ Loan Application  
→ Credit Assessment  
→ Sanction  
→ Disbursement  
→ Utilization  
→ Interest Calculation  
→ Repayment  
→ Closure / Renewal

---

## Banking Terms You MUST Understand 

| Term | What it means for backend code |
|-----|--------------------------------|
| Sanction | Maximum amount the system is allowed to approve |
| Disbursement | Event where money is released |
| Outstanding | Current unpaid balance |
| Utilization | Amount actually used by the customer |
| Tenure | Used to calculate end date |
| DP (Drawing Power) | Maximum withdrawable amount |
| NPA | Loan status flag (non-performing) |
| Moratorium | EMI skip logic |
| Penal Interest | Extra interest rate for violations |

---

## BEFORE CALCULATIONS – What Must Be Clear

Before any interest or EMI calculation, the system must know:

- Whether the loan is **CC or WCDL**
- Whether it is a **fresh loan or a conversion**
- The **sanctioned amount**
- The **interest model** (simple / fixed / benchmark-linked)
- The **tenure** and start date
- Whether **collateral requirements** are met
- Whether the loan is **eligible to be sanctioned**

Only after these checks can calculations be trusted.

---

## What is Cash Credit (CC)?

Cash Credit is a **working capital facility**, not a fixed loan.

- Bank gives a business a **credit limit**
- Business uses money **daily**
- Balance goes **up and down**
- Interest is charged **daily** on the amount used
- No fixed repayment schedule

> Backend view: CC is **balance-driven**, not schedule-driven.

---

## What is WCDL?

Working Capital Demand Loan (WCDL) is a **structured working capital loan**.

- Loan amount is **fixed**
- Given for a **short tenure**
- Repayment plan is **predefined**
- Interest calculated on **outstanding principal**

> Backend view: WCDL is **schedule-driven** and tenure-based.

---

## What Does “Convert CC to WCDL” Mean?

Conversion does **NOT** mean giving extra money.

It means:
- Taking a **portion of already used CC amount**
- Moving it into a **separate WCDL loan account**
- Fixing tenure and repayment for that portion

---

## Why Convert CC to WCDL?

Banks and businesses prefer this because:

- CC interest is **daily and floating**
- WCDL interest is **more predictable**
- Helps businesses **plan cash flow**
- Frees up CC limit for daily operations

---

## Backend Implication of CC → WCDL Conversion

From a system perspective:

- Reduce CC outstanding balance
- Create a new WCDL loan record
- Link conversion reference
- Ensure **atomic transaction**
---

# 🏦 WCDL Loan Flow – Story

This document explains the **end-to-end flow of a Working Capital Demand Loan (WCDL)**
from a **backend system perspective**, highlighting where each banking and technical
concept comes into play.

The focus is on **loan creation, validation, pricing, sanction, and disbursement**.

---

## 1. Customer Enters the System

A business customer already exists in the bank’s ecosystem.

**Preconditions:**
- Customer status is ACTIVE
- KYC is completed
- Business profile is available

At this stage, the system only verifies **identity and eligibility to apply**.

> No loan exists yet.

---

## 2. Loan Application is Created

The customer applies for a **Working Capital Demand Loan (WCDL)**.

**Application details include:**
- Loan Type: WCDL
- Requested Amount
- Requested Tenure
- Purpose: Working Capital

**Backend actions:**
- Store application details
- Generate application ID
- Set loan status to `CREATED`

> No money is released, no interest is calculated at this stage.

---

## 3. Aggregation Service Collects Data

Before making any decision, the system must collect data from multiple sources.

The **Aggregation Service** fetches:
- Customer profile data
- Existing Cash Credit (CC) outstanding (if applicable)
- Collateral details
- Credit indicators
- Benchmark reference rates (Repo / EBLR)

All retrieved data is consolidated into a **single eligibility view**.

---

## 4. Validation Service Applies Rules

The **Validation Service** evaluates business and policy rules:

- Is WCDL allowed for this customer?
- Is the requested amount within eligibility limits?
- If CC → WCDL conversion:
  - Is CC outstanding sufficient?
- Is the requested tenure within product limits?
- Is collateral value adequate?

**Outcomes:**
- If any rule fails → application is **REJECTED**
- If all rules pass → status is updated to `VALIDATED`

> Still no interest or EMI calculations are performed.

---

## 5. Product Matrix Defines the Loan Rules

The system consults the **Product Matrix** to determine applicable rules for WCDL.

The Product Matrix defines:
- Maximum allowed tenure
- Allowed benchmark (EBLR / MCLR)
- Rate type (Fixed or Floating)
- Permissible spread range
- Collateral requirements

This matrix **does not perform calculations**; it only defines **what is allowed**.

---

## 6. Price Engine Calculates the Interest Rate

The **Price Engine** is invoked to determine the final Rate of Interest (ROI).

**Inputs:**
- Benchmark type (EBLR)
- Current Repo rate (from RBI feed)
- Risk-based spread

**Calculation logic:**
    - ROI = Benchmark + Spread


**Example:**
- Repo Rate = 6.50%
- Spread = 2.00%
- ROI = 8.50%

**Rate behavior:**
- Fixed → ROI remains constant
- Floating → ROI changes when the benchmark changes

The calculated ROI is attached to the loan.

---

## 7. Loan Sanction Happens

The bank formally approves the loan.

**Backend actions:**
- Finalize sanctioned amount
- Finalize tenure
- Lock ROI
- Record sanction date

**Loan status becomes:**
    - SANCTIONED

> This is the point at which the loan legally comes into existence.

---

## 8. Authorization Matrix Verifies Approval Authority

Before disbursement, approval authority is validated.

The **Authorization Matrix** determines:
- Who is authorized to approve the loan amount
- Whether the approval hierarchy is satisfied

**Outcomes:**
- If authorization fails → sanction is blocked
- If authorization succeeds → loan proceeds

---

## 9. CC → WCDL Conversion (If Applicable)

If the customer already has a Cash Credit (CC) facility:

**Example:**
- CC Limit: ₹50 lakhs
- CC Outstanding: ₹30 lakhs
- WCDL Amount: ₹10 lakhs

**After conversion:**
- CC outstanding reduces to ₹20 lakhs
- A new WCDL loan of ₹10 lakhs is created

**Important notes:**
- No new money is created
- Total exposure remains unchanged

The backend ensures this process is executed within **one atomic transaction**.

---

## 10. Disbursement Releases the Funds

The **Disbursement Service** releases the sanctioned funds.

**Validations:**
- Loan status must be `SANCTIONED`
- Disbursement amount ≤ sanctioned amount
- Duplicate disbursement is prevented

**Actions:**
- Loan account is created
- Funds are released to the customer

**Loan status becomes:**
    - DISBURSED


For WCDL, the entire amount is considered **utilized immediately**.

---

## 11. Interest Calculation (Conceptual Awareness)

Interest is calculated on:
- Outstanding principal
- Not on the sanctioned amount

Interest depends on:
- ROI
- Tenure
- Date calculations

For floating-rate loans:
- ROI changes when the benchmark changes

> In this project, interest calculation is **understood conceptually** but not implemented post-disbursement.

---

## 12. Repayment, SMA, and NPA (Out of Scope)

Conceptually:
- Repayment reduces outstanding balance
- Overdue days determine SMA and NPA status

In this project:
- Repayment, SMA, and NPA handling are **out of scope**
- Only conceptual awareness is required

---



