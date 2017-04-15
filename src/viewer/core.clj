(ns viewer.core
  "This program generates a HTML static page with the logs from a
  Baoqu event."
  (:gen-class)
  (:require [viewer.utils :as utils]
            [viewer.generator :as generator]))



(defn -main
  "The program's flow starts when the main function is run, receiving
  the `.sqlite` path as the main argument. The program checks that the
  file exists and if it does, calls the ... function"
  [& [path]]
  (if (utils/path-exists? path)
    (generator/generate-log path)
    (utils/fail "ERROR: The file" path "doesn't exists")))
