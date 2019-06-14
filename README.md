# buddy-example

This project shows a simple method to restrict access to certain routes with the `buddy` security library for Clojure.

This project generated using Luminus version "3.36" using the following command to start the project.


    lein new luminus buddy-example +auth


## Notes

The namespace `buddy-example.routes.restricted` has an example route that requires restriction.

The login and logout routes are in `buddy-example.routes.auth`.

The Luminus default `buddy-example.middleware` file has been changed to redirect to `/login' if a restricted page is accessed without a prior successful login.


## Links to review


- [https://github.com/funcool/buddy](https://github.com/funcool/buddy)
- [https://github.com/funcool/buddy-auth/blob/master/examples/session/src/authexample/web.clj](https://github.com/funcool/buddy-auth/blob/master/examples/session/src/authexample/web.clj)
- [http://www.luminusweb.net/docs/routes.html#restricting_access](http://www.luminusweb.net/docs/routes.html#restricting_access)



## Running

To start, run:

    lein run 

## License

Copyright © 2019 Brad Lucas

