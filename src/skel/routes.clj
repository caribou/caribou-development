(ns skel.routes
  (:require [caribou.app.pages :as pages]))

(def routes
  [["" :home []]])

(def pages
  {:home {:GET {:controller 'home 
                :action 'home 
                :template "home.html"}}})

(defn page-tree
  []
  (pages/build-page-tree routes pages))

(defn gather-pages
  []
  (try
    (pages/all-pages)
    (catch Exception e
      (page-tree))))
