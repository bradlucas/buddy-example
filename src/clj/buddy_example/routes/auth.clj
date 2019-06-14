(ns buddy-example.routes.auth
  (:require
    [buddy-example.layout :as layout]
    [clojure.java.io :as io]
    [buddy-example.middleware :as middleware]
    [ring.util.http-response :as response]
    [ring.util.response :refer [redirect]]
    ))

(defn login-page [request]
  (layout/render request "login.html"))


;; Authentication
;; @see https://github.com/funcool/buddy-auth/blob/master/examples/session/src/authexample/web.clj

(def authdata
  "Global var that stores valid users with their
   respective passwords."
  {:admin "secret"
   :test "secret"})

;; Authentication Handler
;; Used to respond to POST requests to /login.

(defn login-authenticate
  "Check request username and password against authdata
  username and passwords.
  On successful authentication, set appropriate user
  into the session and redirect to the value of
  (:next (:query-params request)). On failed
  authentication, renders the login page."
  [request]
  (let [username (get-in request [:form-params "username"])
        password (get-in request [:form-params "password"])
        session (:session request)
        found-password (get authdata (keyword username))]
    (if (and found-password (= found-password password))
      ;; username and password are correct
      (let [;; next-url (get-in request [:query-params :next] "/")
            next-url (get-in request [:params :next] "/")
            updated-session (assoc session :identity (keyword username))]
        ;;(clojure.pprint/pprint request)
        (-> (redirect next-url)
            (assoc :session updated-session)))
      
      ;; login failed so reshow login page
      (do
        (clojure.pprint/pprint (get-in request [:form-params]))
        (layout/render request "login.html" (get-in request [:form-params]))
        )


      ;; (let [content (slurp (io/resource "login.html"))]
      ;;   (render content request))

      )))


(defn logout-page [request]
  (-> (redirect "/")
      (assoc :session {})))

(defn auth-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/login" {:get login-page
              :post login-authenticate}]
   ["/logout" {:get logout-page}]])

