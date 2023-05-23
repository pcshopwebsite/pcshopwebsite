@echo off

REM create assets folder structure
if not exist assets\css mkdir assets\css
if not exist assets\images mkdir assets\images
if not exist assets\js mkdir assets\js

REM create src folder structure
if not exist src\app mkdir src\app
if not exist src\assets mkdir src\assets
if not exist src\assets\images mkdir src\assets\images
if not exist src\environments mkdir src\environments
if not exist src\environments\environment.prod.ts type nul > src\environments\environment.prod.ts
if not exist src\environments\environment.ts type nul > src\environments\environment.ts
if not exist src\favicon.ico type nul > src\favicon.ico
if not exist src\index.html type nul > src\index.html
if not exist src\main.ts type nul > src\main.ts
if not exist src\polyfills.ts type nul > src\polyfills.ts
if not exist src\styles.css type nul > src\styles.css
if not exist src\test.ts type nul > src\test.ts
if not exist src\tsconfig.app.json type nul > src\tsconfig.app.json
if not exist src\tsconfig.json type nul > src\tsconfig.json
if not exist src\tsconfig.spec.json type nul > src\tsconfig.spec.json
if not exist src\typings.d.ts type nul > src\typings.d.ts

echo Project structure created successfully!
pause
