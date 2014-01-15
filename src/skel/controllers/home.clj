(ns skel.controllers.home
  (:use [schmetterling.core :only (debugger)])
  (:require [caribou.model :as model]
            [caribou.app.controller :as controller]))

(defn home
  [request]
  (let [b 1 
        a 0]
    ;; (/ b a)
    (controller/render 
     (assoc request 
       :verbed "Started"))))
