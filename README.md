## Rushshopping
Rushshopping is a RESTful web service of e-commerce designed for the pattern of promotional purchase.
At current stage, the purchase model and its cycle is simple, order and promotion just allow one product in it,
but it represents a design solution to handle promotional activities, including database design to support it.

### Features
- Based on Spring Boot
- Integrated handling error class in BaseController
- Three layers of model design: Data Object, Business Object and View Object
- Allow to cross origin and share session, so it's convenient for frontend debug
- Flexible transaction control using @Transactional(propagation = Propagation.REQUIRES_NEW)
- Use hibernate-validator to handle parameters validation from web pages

### Todo
- handle the surge of rush purchase at one time
- cache, load balance, server cluster ...