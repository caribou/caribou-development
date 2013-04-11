(ns skel.controllers.home
  (:use caribou.app.controller))

(defn home
  [request]
  (render (assoc request :verbed "Started")))
