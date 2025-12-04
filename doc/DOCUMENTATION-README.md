# Grandview Movie Theater - Code Documentation

## 📚 Complete JavaDoc API Documentation

This project includes comprehensive JavaDoc documentation for all classes and class members in the Grandview Movie Theater Management System.

## 🚀 Quick Start

### Option 1: Use the Overview Page
- Open `Documentation-Overview.html` in your web browser for a user-friendly overview
- This page provides organized access to all documentation with descriptions

### Option 2: Access Full JavaDoc Directly
- Open `target/reports/apidocs/index.html` for the complete JavaDoc
- Navigate through packages, classes, and methods

### Option 3: Use the Batch Script
- Run `open-documentation.bat` on Windows to automatically open the documentation

## 📁 Documentation Structure

```
Final Project/
├── Documentation-Overview.html          # User-friendly overview page
├── open-documentation.bat               # Quick launcher script
├── target/reports/apidocs/              # Generated JavaDoc
│   ├── index.html                       # Main JavaDoc entry point
│   ├── overview-tree.html               # Class hierarchy
│   ├── index-all.html                   # Alphabetical index
│   └── com.example.finalproject/        # Package documentation
│       └── com/example/finalproject/
│           ├── model/                   # Model classes documentation
│           ├── controller/              # Controller classes documentation
│           └── Launcher.html            # Main launcher documentation
```

## 🏗️ Project Architecture

### Model Classes (8 classes)
- **User** - Abstract base class for authentication
- **Client** - Customer information and management
- **Manager** - Administrative user with privileges
- **Movie** - Movie metadata and information
- **Room** - Theater room with capacity
- **Showtime** - Scheduled movie showings
- **Ticket** - Abstract ticket base class
- **MoviePage** - Movie display and ticket tracking

### Controller Classes (10 classes)
- **LoginController** - Authentication handling
- **ClientController** - Client registration
- **ManagerDashboardController** - Manager overview
- **ManagerMovieCatalogController** - Movie CRUD operations
- **ManagerMovieFormController** - Movie form handling
- **ManagerRoomManagementController** - Room CRUD operations
- **ManagerRoomFormController** - Room form handling
- **ManagerShowtimeScheduleController** - Showtime CRUD operations
- **ManagerShowtimeFormController** - Showtime form handling
- **MoviePageController** - Client movie viewing

### Application Entry Point
- **Launcher** - JavaFX application initialization

## 🔧 Technical Details

- **Java Version:** 24
- **JavaFX Version:** 21.0.6
- **Build Tool:** Apache Maven 3.8.5
- **Documentation Tool:** Maven JavaDoc Plugin 3.10.1
- **Generated:** December 3, 2025

## 📖 Documentation Features

### Complete Coverage
- ✅ All public and private methods documented
- ✅ Class-level documentation with purpose and usage
- ✅ Parameter descriptions and return types
- ✅ Exception documentation where applicable
- ✅ Cross-references between related classes

### Navigation Features
- 🔍 Search functionality for quick access
- 🌳 Class hierarchy visualization
- 📦 Package-based organization
- 🔗 Hyperlinked cross-references
- 📋 Alphabetical index of all elements

### Documentation Quality
- Professional HTML formatting
- Responsive design for different screen sizes
- Color-coded syntax highlighting
- Comprehensive method signatures
- Usage examples where applicable

## 🎯 How to Navigate

1. **Start with Overview**: Open `Documentation-Overview.html` for a guided introduction
2. **Browse by Package**: Use package summaries to understand project structure
3. **Explore Classes**: Click on class names to see detailed documentation
4. **Method Details**: Each method includes parameters, return values, and exceptions
5. **Use Search**: Search for specific classes, methods, or keywords
6. **Follow Links**: Click on type names to navigate between related classes

## 🔄 Regenerating Documentation

To regenerate the JavaDoc documentation:

```bash
# Using Maven wrapper
.\mvnw.cmd javadoc:javadoc

# Using installed Maven
mvn javadoc:javadoc
```

The documentation will be generated in `target/reports/apidocs/`

## 📝 Documentation Standards

This documentation follows JavaDoc standards and includes:
- Class-level documentation with purpose and author information
- Method documentation with @param, @return, and @throws tags
- Field documentation for important class members
- Package-level documentation for architectural overview
- Cross-references using @see and @link tags

## 🎬 About the Project

The Grandview Movie Theater Management System is a JavaFX-based application that provides:
- User authentication for clients and managers
- Movie catalog management with CRUD operations
- Theater room management and capacity tracking
- Showtime scheduling and management
- Client registration and movie browsing
- CSV-based data persistence

For more information about using the application, refer to the class documentation or the main project README.