(ns buddy-example.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[buddy-example started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[buddy-example has shut down successfully]=-"))
   :middleware identity})
