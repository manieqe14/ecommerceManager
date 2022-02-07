REST API for managing woocommerce orders (including issuing invoices, generating selling statistics) 

- authorization and authentication with Basic or JWT Token
- access to multiple shops

FUTURE:
- integration with sending services like inpost.pl, wysylam.allegro.pl

Creating user:

POST /createUser
-d { 
  "username": "xx",
  "password": "xx"
  }
  
- default ROLE - User  
- access - all


Getting ALL users:

GET /users

-access - ADMIN

Adding shop:
POST /shop/addShop
-d {
    "siteUrl" :"xx",
    "username" : "xx",
    "password" : "xx",
    "name" : "xx"
}

