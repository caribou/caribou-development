{:logging {:loggers [{:type :stdout :level :debug}
                     ;; {:type :remote :host "beast.local" :level :debug}
                     ;; {:type :file :file "caribou-logging.out" :level :debug}
                     ]}
 :database {:classname    "org.h2.Driver"
            :subprotocol  "h2"
            :host         "localhost"
            :subname      "file:/tmp/caribou_development"
            :user         "h2"
            :password     ""}
 :controller {:namespace "skel.controllers" :reload :always}
 :cache-templates :never}
