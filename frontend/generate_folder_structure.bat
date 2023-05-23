@echo off

REM Create folder structure
mkdir src
cd src
mkdir app
cd app
mkdir components
cd components

REM Authentication
mkdir authentication
cd authentication
mkdir login
mkdir register
mkdir email-verification
mkdir password-reset
cd ..

REM Product Listings
mkdir product-listings
cd product-listings
mkdir product-details
mkdir product-list
mkdir search
cd ..

REM Shopping Cart
mkdir shopping-cart
cd shopping-cart
mkdir cart
mkdir checkout
cd ..

REM User Reviews
mkdir user-reviews
cd user-reviews
mkdir add-review
mkdir product-reviews
cd ..

REM User Profile
mkdir user-profile
cd user-profile
mkdir edit-profile
mkdir order-history
cd ..

REM Categories
mkdir categories
cd categories
mkdir category-list
mkdir category-details
cd ..

REM Wishlist
mkdir wishlist
cd wishlist
mkdir wishlist-items
cd ..

REM Shared
mkdir shared
cd shared
mkdir header
mkdir footer
mkdir not-found
cd ..

cd ..

REM Other app folders
mkdir services
mkdir models
mkdir guards
mkdir interceptors
mkdir utils
mkdir admin
mkdir analytics

REM Admin
cd admin
mkdir components
mkdir services
cd ..

REM Analytics
cd analytics
mkdir components
mkdir services
cd ..

cd ..

REM Other src folders
mkdir assets
mkdir environments

echo Project structure created successfully!
pause
