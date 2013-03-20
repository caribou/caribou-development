{:logging {:loggers [{:type :stdout :level :debug}
                     ;; {:type :remote :host "beast.local" :level :debug}
                     ;; {:type :file :file "caribou-logging.out" :level :debug}
                     ]}
 :database {:classname    "org.h2.Driver"
            :subprotocol  "h2"
            :host         "localhost"
            ;; HACK HACK HACK!!!
            :subname		  "file:/tmp/caribou_development"
            :user         "h2"
            :password     ""}
 :controller {:namespace  "skel.controllers" :reload true}
 :cache-templates :never}
