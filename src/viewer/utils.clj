(ns viewer.utils
  "This namespace is used to hold a bunch of auxiliar functions")

(defn path-exists?
  "This method receives a path and instantiates a
  `clojure.java.io/file` object to check if the corresponding file
  exists."
  [path]
  (-> path
      (clojure.java.io/file)
      (.exists)))

(defn fail
  "If anything causes the program to fail during the execution, this
  method will be called. A comprehensive error message will be written
  printed out and the program will close with the `1` error code."
  [& message-parts]
  (apply println message-parts)
  (System/exit 1))
