(ns viewer.core
  "This program generates a HTML static page with the logs from a
  Baoqu event."
  (:gen-class)
  (:require [clojure.tools.cli :refer [parse-opts]]
            [viewer.utils :as utils]
            [viewer.generator :as generator]))

(def cli-options
  "We start defining the interface to the command line tool. We will
  have three arguments:

  - One for the input file, which is required
  - One for the output file, which will default to `baoqu_log.html`
  - And finally, an option to print the help"
  [["-i" "--input INPUT_FILE" "The sqlite database to read from"]
   ["-o" "--output OUTPUT_FILE" "The HTML file to write to"
    :default "baoqu_log.html"
    :default-desc "Defaults to baoqu_log.html"]
   ["-h" "--help" "Shows this help"]])

(defn -main
  "The program's flow then starts when the main function is run, first
  parsing the received arguments and checking that either a `help`
  option is received or the `input` option is.

  If it has the required arguments, it will check that the input
  sqlite file exists, running the `generate-log` function if
  everything is ok. If the file doesn't exist, the program will show
  an error message and exit with an exit code of `1`."
  [& args]
  (let [{:keys [options summary]} (parse-opts args cli-options)
        {:keys [input output help]} options]

    (when help
      (utils/usage summary)
      (System/exit 0))

    (when-not input
      (utils/fail summary "ERROR: An input is required"))

    (if (utils/path-exists? input)
      (generator/generate-log input output)
      (utils/fail summary "ERROR: The file" input "doesn't exists"))))
