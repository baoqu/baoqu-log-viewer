(ns viewer.style
  "This namespace will hold the styles for the output HTML")

(def style [[:* {:box-sizing "border-box"}]
            [:html {:font-family "\"Open Sans\", Arial, sans-serif"
                    :padding 0
                    :margin 0}]
            [:body {:padding 0
                    :margin 0}]
            [:header {:color "#fff"
                      :background-color "#31b2c5"
                      :display "flex"
                      :padding "1rem"
                      :position "fixed"
                      :top 0
                      :width "100%"}]
            [:header [:.nav {:flex 1
                             :margin "0 0 0 1rem"
                             :padding 0}
                      [:li {:display "inline"
                            :margin-right "1rem"}]
                      [:a {:color "white"}]]]
            [:h1 {:font-size "1.25rem"
                  :line-height 1
                  :margin 0}]
            [:h2 {:font-size "2rem"
                  :margin 0
                  :text-transform "uppercase"
                  :color "inherit"
                  :font-weight "lighter"}]
            [:h2 {:color "#888"}]  ;; will it work?
            [:.logo-name {:font-size "1.25rem"
                          :line-height 1
                          :margin-right "1rem"
                          :padding-right "1rem"
                          :border-right "1px solid rgba(255, 255, 255, 0.3)"}]
            [:select {:border "1px solid #fff"
                      :background "white"
                      :padding "0.1rem 1rem 0.1rem 0.2rem"}]
            [:.content {:justify-content "center"}]
            [:section {:padding "5rem 1rem 0"
                       :max-width "65rem"
                       :margin "auto"}]
            [:.list {:padding 0}
             [:li {:list-style-type "none"
                   :margin-bottom "1rem"
                   :padding-bottom "1rem"
                   :border-bottom "1px solid #eee"}]]
            [:.idea {:font-size "1.2rem"
                     :font-weight "bold"}]
            [:.meta {:font-size "0.9rem"
                     :color "#888"
                     :margin-top "0.2rem"}]
            [:.tag {:font-size "0.8rem"
                    :background-color "#ddd"
                    :padding "0 0.2rem"
                    :border-radius "4px"}]])
