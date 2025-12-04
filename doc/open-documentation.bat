@echo off
echo Opening Grandview Movie Theater Documentation...
echo.
echo Available documentation:
echo 1. Overview Page (this document)
echo 2. Full JavaDoc API Documentation
echo 3. Class Hierarchy Tree
echo 4. All Classes Index
echo.

REM Try to open the overview page
if exist "Documentation-Overview.html" (
    start "" "Documentation-Overview.html"
    echo Overview page opened in your default browser.
) else (
    echo Documentation-Overview.html not found.
)

REM Also open the main JavaDoc index
if exist "target\reports\apidocs\index.html" (
    echo.
    echo You can also access the full JavaDoc at:
    echo %CD%\target\reports\apidocs\index.html
) else (
    echo JavaDoc not found. Run 'mvnw javadoc:javadoc' to generate it.
)

echo.
echo Documentation files:
echo - Documentation-Overview.html (This overview)
echo - target\reports\apidocs\index.html (Full JavaDoc)
echo - target\reports\apidocs\overview-tree.html (Class hierarchy)
echo - target\reports\apidocs\index-all.html (All classes index)
echo.
pause