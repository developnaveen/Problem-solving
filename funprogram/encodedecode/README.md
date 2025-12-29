# 🔐 Encode and Decode – Security Concepts

This document explains **hashing, encryption (encoding), and decryption (decoding)**
using commonly used security algorithms such as **BCrypt, AES-GCM, and RSA-OAEP**.

---

## 🔹 Hashing

- **BCrypt**
- One-way process
- Used to securely store sensitive data like passwords
- Original data **cannot be retrieved**

### Usage
- Password storage in databases
- Authentication systems

📌 Hashing is **not reversible**.

---

## 🔹 Encoding / Encryption

Encryption is a **two-way process**.
Data can be encrypted and later decrypted using keys.

---

## 🔐 AES (Symmetric Encryption)

- Uses the **same key** for encryption and decryption
- Very fast
- Suitable for **large data**
- Common mode used: **AES-GCM**

---

### 🔹 What is GCM (Galois/Counter Mode)?

GCM is an **authenticated encryption mode** that provides:
- **Confidentiality** (encryption)
- **Integrity** (tamper detection)

---

### 🔸 Components used in GCM

#### 1️⃣ IV (Initialization Vector)
- Not a secret key
- Random or unique value
- Ensures the same data encrypted multiple times produces different ciphertext
- Must be **unique for every encryption**

📌 IV is sent along with encrypted data.

---

#### 2️⃣ Authentication Tag
- Generated during encryption
- Verifies that data:
  - Was not modified
  - Is authentic
- If data is changed, decryption fails

📌 Authentication tag checks **integrity**, not data size.

---

## 🔐 RSA (Asymmetric Encryption)

- Uses a **key pair**
  - Public key → encrypt
  - Private key → decrypt
- Slower than AES
- Used for **small data**
- Mainly used to encrypt **AES keys**

---

### 🔹 What is OAEP?

**OAEP = Optimal Asymmetric Encryption Padding**

RSA commonly uses:
```bash 
RSA/ECB/OAEPWithSHA-256AndMGF1Padding
```

---

### 🔸 OAEP Includes:
- **SHA-256** → Hash function
- **MGF1** → Mask Generation Function
- Adds randomness and security
- Protects against padding oracle attacks

📌 OAEP is the **recommended and secure padding** for RSA.

---

## 🔓 Decoding / Decryption

### 🔐 AES Decryption
Requires:
- Same AES key
- IV
- Authentication tag

Encrypted data structure:
```bash
[ IV | Ciphertext | Authentication Tag ]
```

If authentication tag verification fails → decryption fails.

---

### 🔐 RSA Decryption
- Uses the **private key**
- Private key is securely stored on the server
- Used to decrypt:
  - AES keys
  - Small encrypted values

---

## 🧠 Summary

- **BCrypt** → One-way hashing (password storage)
- **AES-GCM** → Encrypts data and ensures integrity
- **RSA-OAEP** → Securely encrypts AES keys
- **IV** → Provides randomness
- **Authentication Tag** → Detects tampering

---

## 🎯 Best Practice

> Use **hashing for passwords**, **AES-GCM for data encryption**,  
> and **RSA-OAEP for secure key exchange**.
