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
   :comments (db/get-all-comments path)
   :circles (db/get-all-circles path)})

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
  [{:keys [votes ideas comments circles]}]
  (html5 [:head
          [:title "Baoqu log"]
          [:meta {:http-equiv "Content-Type" :content "text/html; charset=UTF-8"}]
          [:style template-css]]
         [:body
          [:header
           [:span.logo]
           [:div.logo-name "Baoqu"]
           [:h1 "EVENT NAME"]
           [:ul.nav
            [:li [:a {:href "#ideas"} "Ideas"]]
            [:li [:a {:href "#comments"} "Comments"]]]
           [:select.circles {:name ""}
            [:option {:value ""} "All Circles"]
            (for [{:keys [id]} circles]
              [:option {:value id} (str "Circle " id)])]]
          [:div.content
           [:section#ideas
            [:h2 "IDEAS"]
            [:ul {:class "list ideas"}
             (for [{:keys [name users votes]} ideas]
               [:li
                [:div.idea name]
                [:div.meta [:strong (str votes "votes")] (str "|" users)]])]]]
          [:section#comments
           [:h2 "COMMENTS"]
           [:ul {:class "list comments"}
            (for [{:keys [user body circle]} comments]
              [:li
               [:div.comment body]
               [:div.meta [:span.tag (str "C" circle)] (str " " user)]])]]
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
