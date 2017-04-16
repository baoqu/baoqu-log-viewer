(ns viewer.cljs-compiler
  "This namespace contains the functions related to the compilation
  process of the CLJS code."
  (:require [cljs.build.api :as cljs]))

(defn cljs-compile
  "Here we use the CLJS compile API to compile run the compilation
  process."
  []
  (cljs/build "src-cljs" {:main 'viewer-ui.core
                          :output-to "out/main.js"}))
