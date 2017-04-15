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
          [:div#ideas
           [:h1 "IDEAS"]
           [:ul
            (for [{:keys [id name users votes]} ideas]
              [:li (str "[" votes "] " users " :: " name)])]]
          [:div#comments
           [:h1 "COMMENTS"]
           [:ul
            (for [{:keys [id user body date circle]} comments]
              [:li (str "[" user " - C" circle "] " body)])]]]))

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
