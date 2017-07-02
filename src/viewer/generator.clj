(ns viewer.generator
  "This namespace will hold the functions needed to extract the
  required information from the database and use it to generate the
  HTML output with the help of a hiccup template."
  (:require [hiccup.page :refer [html5 include-js]]
            [garden.core :as garden]
            [viewer.db :as db]
            [viewer.cljs-compiler :as cljsc]
            [viewer.style :refer [style]]))

(defn get-data-map
  "With this function we get all the needed data from the database and
  we return it in a map for the generator to use."
  [path]
  {:votes (db/get-all-votes path)
   :ideas (db/get-all-ideas path)
   :comments (db/get-all-comments path)})

(def template-css
  "We use the power of [garden](https://github.com/noprompt/garden) to
  generate the required CSS of our template."
  (garden/css style))

(def ^:const js-includes
  "This is a list of the JS files that will be included in the
  template."
  ["out/goog/base.js" "out/main.js"])

(defn generate-template
  "We then provide a template to hold the extracted data."
  [{:keys [votes ideas comments]}]
  (html5 [:head
          [:title "Baoqu log"]
          [:style template-css]
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
              [:li (str "[" user " - C" circle "] " body)])]]
          (apply include-js js-includes)]))

(defn generate-log
  "And using those two pieces, we create and run a pipeline to compile
  the CLJS code, get the data, build the final output with the
  structure from the template and finally write that HTML code to the
  output file provided by the user."
  [input output]
  (cljsc/cljs-compile)
  (->> input
       (get-data-map)
       (generate-template)
       (spit output)))
