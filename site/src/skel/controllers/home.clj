(ns skel.controllers.home
  (:use caribou.app.controller)
  (:require [caribou.model :as model]))

(defn home
  [request]
  (render request))
