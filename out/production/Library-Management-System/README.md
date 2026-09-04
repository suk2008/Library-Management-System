# 📚 Library Management System
A multi-branch Java-based Library Management System adhering to **SOLID principles** and common **design patterns**.

---

## ✨ Features

### 1. Book Management
- `Book` class with attributes: **title, author, ISBN, publication year**.
- Add, remove, and update books in the library inventory.
- Search books by **title, author, or ISBN**.

### 2. Patron Management
- `Patron` class to represent library members.
- Add new patrons and update their information.
- Track patron borrowing history.

### 3. Lending Process
- Book checkout and return functionalities.

### 4. Inventory Management
- Track available and borrowed books.
- Prevent checkout of unavailable books.
- Maintain borrowing records linked to patrons.

---

## 🔧 Advanced Extensions
- **Multi-Branch Support** → Track inventory separately across physical branches.
- **Lending & Transfers** → Checkout, return, and inter-branch transfer workflows.
- **Reservations (Observer Pattern)** → Queue system that notifies patrons when checked-out books become available.
- **Recommendations (Strategy Pattern)** → Easily swap recommendation strategies based on user history or preference.

---

## ⚙️ Setup & Compilation
1. Ensure **JDK 17+** is installed.
2. Clone the repository:
   ```bash
   git clone https://github.com/suk2008/Library-Management-System.git
   ```
3. Open the project in IntelliJ IDEA or Eclipse.
4. Run the LibraryManagementDemo.java file.
5. Follow the menu displayed on the console.

## Project Structure
src/
├── constants/
│    └── BookStatus.java   (enum)
├── entity/
│    ├── Book.java
│    └── Patron.java   (enum)
│
├── RecommendationStrategies/
│    ├── RecommendationStrategy.java
│    └── HistoryBasedRecommendationStrategy.java
│
├── branch/
│    ├── LibraryBranch.java
│    └── BranchTransferService.java
│
├── system/
│    └── LibrarySystem.java
│
├── util/
│    └── LoggerUtil.java   (optional wrapper for java.util.logging)
│
└── LibraryManagementSystemMain.java   (main entry point)

## Class Diagram
------------------------
@startuml
enum BookStatus {
Available,
Borrowed,
Reserved,
InTransit;
}

class Book {
- isbn : String
- title : String
- author : String
- publicationYear : int
- genre : String
- status : BookStatus
- reservationQueue : Queue<Patron>
+ Book(isbn, title, author, publicationYear, genre)
+ getIsbn() : String
+ getTitle() : String
+ getAuthor() : String
+ getGenre() : String
+ getStatus() : BookStatus
+ setStatus(status : BookStatus) : void
+ getReservationQueue() : Queue<Patron>
+ addReservation(patron : Patron) : void
+ notifyNextReservation() : Patron
  }

class Patron {
- patronId : String
- name : String
- email : String
- borrowingHistory : List<Book>
- preferredGenres : List<String>
+ Patron(patronId, name, email)
+ getPatronId() : String
+ getName() : String
+ getBorrowingHistory() : List<Book>
+ getPreferredGenres() : List<String>
+ addBorrowHistory(book : Book) : void
+ notifyAvailability(book : Book) : void
  }

interface RecommendationStrategy {
+ recommend(patron : Patron, inventory : List<Book>) : List<Book>
  }

class HistoryBasedRecommendationStrategy {
+ recommend(patron : Patron, inventory : List<Book>) : List<Book>
  }

class LibraryBranch {
- branchId : String
- branchName : String
- inventory : Map<String, Book>
+ LibraryBranch(branchId, branchName)
+ getBranchId() : String
+ getInventory() : Map<String, Book>
+ addBook(book : Book) : void
+ removeBook(isbn : String) : void
  }

class BranchTransferService {
+ transferBook(isbn : String, source : LibraryBranch, destination : LibraryBranch) : boolean
  }

class LibrarySystem {
- branches : Map<String, LibraryBranch>
- patrons : Map<String, Patron>
+ addBranch(branch : LibraryBranch) : void
+ addPatron(patron : Patron) : void
+ checkoutBook(branchId : String, isbn : String, patronId : String) : Boolean
+ returnBook(branchId : String, isbn : String) : Boolean
+ reserveBook(branchId : String, isbn : String, patronId : String) : Boolean
  }

Book "1" --> "*" Patron : reservationQueue
Patron "1" --> "*" Book : borrowingHistory
RecommendationStrategy <|.. HistoryBasedRecommendationStrategy
LibraryBranch "1" --> "*" Book : inventory
LibrarySystem "1" --> "*" LibraryBranch : branches
LibrarySystem "1" --> "*" Patron : patrons
BranchTransferService --> LibraryBranch
BranchTransferService --> Book
@enduml
-------------------------------------------

## Future Improvements

Some features that can be enhanced later:

- Database integration (MySQL)
- Spring Boot REST APIs
- Login and Authentication
- File Storage
- JUnit Test Cases
- Logging
- Docker Support

---

## Author
**Sukriti Kalyani**