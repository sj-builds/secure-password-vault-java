# 🔐 Secure Password Vault

<div align="center">

**A Secure Password Manager built in Java with a focus on Security, Clean Architecture, and Object-Oriented Programming.**

*Store • Protect • Generate • Manage*

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Object%20Oriented-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-v2.0%20In%20Progress-success?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

</div>

---

# 📖 Overview

**Secure Password Vault** is a Java console application designed to securely manage credentials while demonstrating modern software engineering principles.

Unlike a basic CRUD project, this application focuses on **security**, **maintainability**, and **clean architecture**.

The project applies Object-Oriented Programming, File Handling, Exception Handling, Secure Password Generation, and Encryption concepts to build a practical password management system.

---

# ✨ Features

## Current Features

- Add credentials
- View saved credentials
- Search credentials
- Update username
- Update password
- Delete credentials
- Generate strong passwords
- Password strength analyzer
- File persistence
- Menu-driven console interface
- Input validation
- Exception handling

---

## Security Features (v2.0)

- AES Encryption for stored passwords
- Automatic data saving
- Password masking
- Reveal password option
- Duplicate credential detection
- Strong password validation
- Secure password generation
- JSON-based storage

---

## Planned Features

- Master Password Authentication
- Password History
- Credential Statistics Dashboard
- Backup & Restore
- Import / Export
- Credential Sorting
- Search by Username
- Last Updated Timestamp
- Future GUI Version (JavaFX)

---

# 🛠 Tech Stack

- Java
- Object-Oriented Programming
- Collections Framework
- SecureRandom
- AES Encryption *(v2.0)*
- JSON Storage *(Jackson)*
- File Handling
- Exception Handling

---

# 📂 Project Structure

```text
SecurePasswordVault/
│
├── src/
│   ├── Credential.java
│   ├── PasswordManager.java
│   ├── PasswordGenerator.java
│   ├── PasswordStrength.java
│   ├── FileManager.java
│   └── PasswordVault.java
│
├── data/
│   └── passwords.json
│
├── README.md
├── LICENSE
└── .gitignore
```

---

# 🏗 Architecture

```
                 User
                  │
                  ▼
          PasswordVault
                  │
                  ▼
         PasswordManager
        ┌─────────┼─────────┐
        ▼         ▼         ▼
 PasswordGenerator FileManager PasswordStrength
        │         │
        ▼         ▼
 AES Encryption  passwords.json
```

---

# 🔑 Core Functionalities

### Credential Management

- Add
- Search
- Update
- Delete
- View

---

### Password Generator

- Configurable length
- Uppercase letters
- Lowercase letters
- Numbers
- Symbols
- SecureRandom

---

### Password Strength Analyzer

Checks:

- Password length
- Uppercase letters
- Lowercase letters
- Numbers
- Symbols

Returns:

- Very Weak
- Weak
- Medium
- Strong
- Very Strong

---

### Secure Storage

Version 2.0 stores credentials as encrypted JSON.

Example structure:

```json
[
  {
    "website": "GitHub",
    "username": "john_doe",
    "password": "Encrypted String"
  }
]
```

Passwords are never intended to be stored as plain text.

---

# 🔒 Security Roadmap

| Feature | Status |
|---------|--------|
| Password Generator | ✅ |
| Password Strength Checker | ✅ |
| File Persistence | ✅ |
| AES Encryption | 🚧 |
| JSON Storage | 🚧 |
| Password Masking | 🚧 |
| Auto Save | 🚧 |
| Master Password | 📅 |
| Password History | 📅 |
| Backup & Restore | 📅 |

---

# 🚀 Getting Started

## Clone

```bash
git clone https://github.com/sj-builds/secure-password-vault-java.git
```

## Navigate

```bash
cd secure-password-vault-java
```

## Compile

```bash
javac *.java
```

## Run

```bash
java PasswordVault
```

---

# 📚 Concepts Demonstrated

- Object-Oriented Programming
- Classes & Objects
- Constructors
- Encapsulation
- ArrayList
- Methods
- Exception Handling
- File Handling
- SecureRandom
- Encryption
- JSON Processing
- Clean Code Principles

---

# 📸 Screenshots

```
screenshots/
├── home.png
├── add.png
├── search.png
├── generator.png
├── dashboard.png
└── encryption.png
```

---

# 🎯 Future Improvements

- GUI using JavaFX
- Cloud synchronization
- Multi-user support
- Password expiration alerts
- Dark mode interface
- Database integration
- Unit testing with JUnit
- Maven build support

---

# 🤝 Contributing

Contributions are welcome.

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Push the branch
5. Open a Pull Request

---

# 📄 License

Distributed under the MIT License.

---

# 👨‍💻 Author

**Shreyansh Jain**

BCA Student | Java Developer | Software Engineering Enthusiast

GitHub: https://github.com/sj-builds

LinkedIn: https://linkedin.com/in/shreyanshjain-tech

---

<div align="center">

⭐ **If you found this project interesting, consider giving it a star!**

</div>