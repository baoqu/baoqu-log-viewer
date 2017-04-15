(defproject baoqu-log-viewer "0.1.0-SNAPSHOT"
  :description "Generate a HTML static page with the logs from a Baoqu
  event"
  :license {:name "Apache 2.0 License"
            :url "https://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha15"]
                 [org.clojure/clojurescript "1.9.521"]
                 [hiccup "1.0.5"]
                 [com.layerware/hugsql "0.4.7"]
                 [org.xerial/sqlite-jdbc "3.16.1"]]
  :aot [viewer.core]
  :main viewer.core
  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-ancient "0.6.10"]
            [lein-marginalia "0.9.0"]]
  :aliases {"doc" ["marg" "-f" "index.html"]})
