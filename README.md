## Rushshopping
It is a RESTful web service of e-commerce designed for limited-time deals.
At current stage, the purchase model and cycle is simple, order and promotion just allow one product in it,
but it represents a design solution that how to solve the relationship among products, promotions and orders, including database design to support them.

### Features
- Based on Spring Boot
- Integrated handling error class in BaseController
- Three layers of model design: Data Object, Business Object and View Object
- Allow to cross origin and share session, so it's convenient for frontend debug
- Flexible transaction control using @Transactional(propagation = Propagation.REQUIRES_NEW)
- Use hibernate-validator to handle parameters validation from web pages

### Todo
- Handle the surge of rushing purchases at one time
- cache, load balance, server cluster ...