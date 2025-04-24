# 🪑 FindMeSeat - Smart Library Seat Reservation System

Reimagining quiet study spaces through a seamless, efficient seat booking experience for libraries and academic environments.

---

## 📖 Overview

**FindMeSeat** is a smart reservation platform that enables university and library users to **search, book, manage, and modify study seat reservations** in real-time. Designed for both **users** and **admins**, the system enhances campus resource utilization and improves access to shared seating infrastructure.

With clean dashboards, role-specific access, and strong validation mechanisms, the system ensures that users reserve only available seats and never double-book.

---

## 🌟 Key Features

### 👤 User-Focused Experience
- 🔐 Secure login/registration (User-only self-registration).
- 🔎 Search seats by type (e.g., 2-Seater Aisle, Recliner).
- 📅 View, create, update, or cancel reservations.
- 🚫 Validation to prevent double-bookings or overlapping reservations.

### 🛠️ Admin Functionality
- 🧠 Login-only access (Admin credentials are created by system).
- 📅 View all reservations for today and tomorrow by default.
- 🎯 Filter by seat type, floor number, date range.
- 🪑 View reservations by seat.
- 👤 View complete booking history by user.
- ❌ Cancel any reservation.

---

## 🛠️ Tech Stack

### Backend
- **Spring Boot**: Lightweight backend framework for MVC structure.
- **Hibernate ORM**: Maps models to database entities.
- **PostgreSQL**: Relational database used for persistence.
- **Jakarta Bean Validation**: For field-level validation.
- **Manual DAO Pattern**: Clean separation of data access logic.
- **Session Management**: HttpSession for role-based login tracking.

### Frontend
- **JSP + JSTL**: For dynamic view rendering.
- **Bootstrap 5**: For clean and responsive UI.
- **Spring Form Tags**: Easy binding and form handling.

### Architecture
- **MVC** (Model-View-Controller)
  - **Model**: Java POJOs (Hibernate Entity Mappings).
  - **View**: JSP with Bootstrap styling.
  - **Controller**: Spring Controllers for routing & logic.

---

## 📂 Functional Directory Structure

---

## 🧪 Validation Highlights

- ⏱️ **Future-Or-Present**: Start datetime must not be in the past.
- 🔁 **No Overlaps**: No booking allowed if it clashes with another user's or the same user's reservation.
- 📏 **Time Constraint**: End must be strictly after start.
- 🪑 **Seat-Level Validation**: Ensure availability before confirming update or creation.

---

## 🛠️ Setup Instructions

### Prerequisites
- Java 17+
- PostgreSQL DB
- Maven
- IntelliJ / Eclipse

---
## ⚠️ Disclaimer
This repository is intended solely for evaluation purposes to showcase my coding skills, design practices, and technical expertise.

## Usage Restrictions
- Unauthorized use, reproduction, modification, or distribution of the code in this repository is strictly prohibited.
- If you wish to use any part of this code, please contact me directly to obtain explicit permission.

## Contact Information
If you have any questions or require further information, feel free to reach out:

- Name: Keya Goswami
- Email: goswami.ke@northeastern.edu
- LinkedIn: https://linkedin.com/in/keya--goswami
