(ns skel.routes
  (:require [caribou.app.pages :as pages]))

(def routes
  [["/" :home 
    [["/yellow" :yellow []]]]])

(def pages
  {:home {:GET {:controller 'home 
                :action 'home 
                :template "home.html"}}
   :yellow {:GET {:controller 'home
                  :action 'yellow
                  :template "yellow.html"}}})

(defn page-tree
  []
  (pages/build-page-tree routes pages))

(defn gather-pages
  []
  (let [db-pages (try 
                   (pages/all-pages) 
                   (catch Exception e nil))]
    (pages/merge-page-trees db-pages (page-tree))))
