@echo off
if not exist bin mkdir bin
javac -d bin -sourcepath src src/com/discussionforum/ui/*.java src/com/discussionforum/ui/components/*.java src/com/discussionforum/service/*.java
if %errorlevel% equ 0 (
    java -cp bin com.discussionforum.ui.Main
) else (
    echo Compilation failed!
    pause
)