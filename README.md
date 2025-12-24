# Student-Record-Management-System
Java Swing desktop application for managing student academic records with CSV persistence and dashboard analytics.
(this was my Semester Project of Semester 7th after completing Java Subject)
[README.md](https://github.com/user-attachments/files/24332951/README.md)

# 🎓 Student Record Management System (SRMS)
A desktop-based Student Record Management System developed using Java Swing, designed to manage, organize, and analyze student academic records efficiently. This project is built as a semester project but follows professional software design principles, making it suitable for academic demonstration and GitHub portfolio showcase.
________________________________________
# 📌 Project Overview
The Student Record Management System (SRMS) provides an easy-to-use graphical interface for managing student data such as:
•	Personal details
•	Academic information
•	CGPA statistics
•	Program-wise records
The system supports CRUD operations, real-time search, sorting, validations, dashboard analytics, and data export features.
________________________________________
# 🖼️ Project Screenshots
 <img width="1086" height="583" alt="dashboard" src="https://github.com/user-attachments/assets/95de3290-b643-46d2-9424-9915016f9e44" />

________________________________________
# ✨ Features
🔹 Core Features
•	Add new student records
•	Update existing student information
•	Delete student records with confirmation dialog
•	View all students in a sortable table
🔹 Dashboard Analytics
•	Total number of students
•	Average CGPA calculation
•	Students above 3.0 CGPA
🔹 Search & Filter
•	Real-time search by Roll Number or Name
•	Column-wise filtering and sorting using dropdowns
🔹 Input Validation
•	Mandatory Roll Number and Name
•	CGPA restricted between 0.0 – 4.0
•	Valid semester input
🔹 Media Support
•	Student photo upload and preview
🔹 Data Persistence
•	CSV-based file storage
•	Backward compatibility for older data formats
🔹 Export Options
•	Export student data to CSV
•	Export student reports to PDF
🔹 UI Enhancements
•	Clean and modern Swing UI
•	Light/Dark mode toggle
•	Responsive layout panels
________________________________________
# 🛠️ Technologies Used
Technology	Purpose
Java (JDK 8+)	Core programming language
Java Swing	Graphical User Interface
AWT	UI event handling
CSV File Handling	Data storage
OOP Concepts	Encapsulation, abstraction
MVC Pattern (Partial)	Code organization
________________________________________
# 🧱 Project Structure
StudentRecordManagementSystem/
│
├── src/
│   ├── Student.java
│   ├── StudentManager.java
│   ├── DashboardPanel.java
│   ├── StudentFormPanel.java
│   ├── StudentTableView.java
│   ├── MainFrame.java
│   └── Utils.java
│
├── data/
│   └── students.csv
│
├── screenshots/
│   ├── dashboard.png
│   ├── students.png
│   └── form.png
│
├── README.md
└── StudentRecordManagementSystem.jar
________________________________________
# 🧠 System Design Concepts
🔸 Object-Oriented Programming
•	Student class encapsulates student data
•	Data operations handled via manager/controller classes
🔸 MVC-inspired Architecture
•	Model: Student.java
•	View: Swing Panels (Dashboard, Forms, Tables)
•	Controller: StudentManager
🔸 Defensive Programming
•	Input validation
•	Confirmation dialogs
•	Exception handling for file operations
________________________________________
# 🚀 How to Run the Project
🔧 Requirements
•	Java JDK 8 or above
•	Windows / Linux / macOS
▶ Compile
javac src/*.java
▶ Run
java MainFrame
________________________________________
# 📂 Data Storage Format (CSV)
roll|name|fatherName|gender|program|semester|cgpa|photoPath
Example:
cs120212060|Aamir Farooq|Muhammad Farooq|Male|Computer Science|8|3.83|photos/aamir.jpg
________________________________________
# 🎯 Use Cases
•	University student record management
•	Departmental academic tracking
•	CGPA analysis and performance monitoring
•	Semester project demonstration
•	Java Swing learning reference
________________________________________
# 👨🎓 Academic Relevance
This project demonstrates:
•	GUI-based desktop application development
•	File handling without databases
•	Clean UI/UX design principles
•	Practical use of Java OOP concepts
________________________________________
# 🔮 Future Enhancements
•	Database integration (MySQL / SQLite)
•	User authentication (Admin / Staff)
•	Role-based access control
•	Cloud storage support
•	Advanced reporting & charts
________________________________________
# 👤 Author
Aamir Farooq
BS Computer Science
Khushal Khan Khattak University, Karak
________________________________________
# 📜 License
This project is developed for educational purposes only.
You are free to use, modify, and share it with proper credit.
________________________________________
# ⭐ If you find this project useful, don’t forget to give it a star on GitHub!

