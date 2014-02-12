(ns skel.routes
  (:require [caribou.app.pages :as pages]))

(def routes
  [["/" :home {:GET {:controller 'home :action 'home :template "home.html"}}
    [["/what" :what {:GET (fn [request] {:status 200 :body "☃☃☃☃☃☃☃☃☃☃☃☃☃☃☃ "})}]
     ["/yellow/:okay" :yellow {:GET (fn [request] {:status 200 :body "itiso wyoyw"}) :POST (fn [request] {:status 200 :body (str (-> request :params :okay) "!! orb")})}]]]])

(defn build-routes
  [routes namespace]
  (pages/bind-actions routes namespace))

(defn gather-pages
  []
  (try 
    (pages/all-pages)
    (catch Exception e nil)))
