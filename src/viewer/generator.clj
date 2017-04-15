(ns viewer.generator
  (:require [hiccup.page :refer [html5]]
            [viewer.db :as db]))

(defn get-data-map
  "With this function we get all the needed data from the database and
  we return it in a map for the generator to use."
  [path]
  {:votes (db/get-all-votes path)
   :ideas (db/get-all-ideas path)
   :comments (db/get-all-comments path)})

(defn generate-template
  "We then provide a template to hold the extracted data."
  [{:keys [votes ideas comments]}]
  (html5 [:head
          [:title "Baoqu log"]
          [:meta {:charset "utf8"}]]
         [:body
          [:div#ideas
           [:h1 "Ideas"]
           [:ul
            (for [{:keys [id name users votes]} ideas]
              [:li (str "[" votes "] " users " :: " name)])]]
          [:div#comments
           [:h1 "Comments"]
           [:ul
            (for [{:keys [id user body date circle]} comments]
              [:li (str "[" user " - C" circle "] " body)])]]]))

(defn generate-log
  "And using those two pieces, we create and run a pipeline to get the
  data, build the final output with the structure from the template
  and finally write that HTML code to the output file provided by the
  user."
  [input output]
  (->> input
       (get-data-map)
       (generate-template)
       (spit output)))
