# WCDL – Working Capital Demand Loan

---

## 1. Types of Loans

- Personal Loans
- Business Loans
  - Term Loan
  - Working Capital Loan
  - MSME Loans (Micro, Small, and Medium Enterprises)
- Others

---

## 2. Mental Model (Backend Developer View)

> **Loan = State Machine + Math + Dates + Rules**

As a backend developer, a loan system mainly involves:
- State transitions (Created → Active → Closed)
- Interest and repayment calculations
- Date-based events (tenure, overdue, NPA)
- Strict business validations

---

## 3. What is WCDL?

**Working Capital Demand Loan (WCDL)** is a short-term loan provided to businesses
to meet day-to-day operational expenses and maintain liquidity.

Key characteristics:
- Used for working capital needs
- Fixed tenure (usually short-term)
- Interest charged on outstanding principal
- Often created by converting part of a Cash Credit (CC) limit

---

## 4. Types of Working Capital Facilities

- Cash Credit (CC)
- Overdraft (OD)
- Working Capital Demand Loan (WCDL)

---

## 5. Cash Credit (CC)

- Operates through a **Cash Credit account**
- Usually linked to a **Current Account** for operations
- Allows flexible withdrawal up to a sanctioned limit
- Interest is charged **only on the amount utilized**
- Commonly secured by inventory and receivables

> Backend note: CC is balance-driven and interest is calculated daily.

---

## 6. Overdraft (OD)

- Operates through a **Current Account**
- Account balance is allowed to go into **negative (overdraft)**
- Interest is calculated only on the **overdrawn amount**
- Overdraft limit is sanctioned by the bank (varies by customer)

> Backend note: OD logic is similar to CC but without stock-based drawing power.

---

## 7. MSME Loans

- Loan amount is disbursed as a **lump sum**
- Fixed tenure with scheduled repayments
- More formal documentation and compliance
- Interest calculated for the **entire loan tenure**
- Designed for Micro, Small, and Medium Enterprises

---

## 8. Comparison: CC vs OD vs WCDL

| Feature        | CC                     | OD                     | WCDL                    |
|----------------|------------------------|------------------------|-------------------------|
| Nature         | Revolving              | Revolving              | Term-like               |
| Disbursement   | As needed              | As needed              | Lump sum                |
| Tenure         | 1 year (renewable)     | 1 year                 | Short fixed tenure      |
| Interest Basis | Daily utilized balance | Daily overdrawn amount | Outstanding principal   |
| Account Type   | CC account             | Current account        | Loan account            |
| EMI Schedule   | No                     | No                     | Yes / Scheduled         |

---

## 9. Key Takeaway

- **CC / OD** → Flexible, balance-based facilities  
- **WCDL** → Structured, tenure-based loan  
- Backend systems must handle:
  - Interest accrual
  - Repayment schedules
  - Status transitions (Active, Overdue, NPA)

---
