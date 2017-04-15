(ns viewer.utils
  "This namespace is used to hold some auxiliar functions.")

(defn path-exists?
  "This method receives a path and instantiates a
  `clojure.java.io/file` object to check if the corresponding file
  exists."
  [path]
  (-> path
      (clojure.java.io/file)
      (.exists)))

(defn usage
  "This method prints the usage string. It is used both by itself and
  from the fail method, isolating the usage string mainaining its
  consistency."
  [summary]
  (println "Options:")
  (println summary))

(defn fail
  "If anything causes the program to fail during the execution, this
  method will be called. A comprehensive error message will be written
  printed out and the program will close with the `1` error code."
  [summary & message-parts]
  (apply println message-parts)
  (usage summary)
  (System/exit 1))
