(ns buddy-example.routes.restricted
  (:require
    [buddy-example.layout :as layout]
    [clojure.java.io :as io]
    [buddy-example.middleware :as middleware]
    [ring.util.http-response :as response]))

(defn restricted-page [request]
  (layout/render request "restricted.html"))

(defn restricted-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats
                 middleware/wrap-restricted]}
   ["/restricted" {:get restricted-page}]])

