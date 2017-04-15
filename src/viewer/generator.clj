(ns viewer.generator
  (:require [hiccup.page :refer [html5]]
            [viewer.db :as db]))

(defn generate-template
  "This function generates the HTML output mixing a predefined
  template and the args it receive"
  [{:keys [votes ideas comments]}]
  (html5 [:head
          [:title "Hola mundo"]
          [:meta {:charset "utf8"}]]
         [:body
          [:div [:h1 "IDEAS"]
           [:ul
            (for [{id :idea-id
                   name :idea-name
                   user :user} votes]
              [:li name])]]]))

(defn get-data-map
  "This function gets all the needed data from the database and
  returns it in a map"
  [path]
  {:votes (db/get-all-votes path)
   :ideas (db/get-all-ideas path)
   :comments (db/get-all-comments path)})

(defn generate-log
  [path]
  (->> path
       (get-data-map)
       (generate-template)
       (spit "/tmp/output.html")))
