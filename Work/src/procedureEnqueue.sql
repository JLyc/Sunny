DBMS_AQ.DEQUEUE_EXTENDED(

   queue_name          			    IN  VARCHAR2,
   dequeue_options_consumer_name    IN  VARCHAR2(30)
   dequeue_options_dequeue_mode     IN  BINARY_INTEGER
   dequeue_options_navigation       IN  BINARY_INTEGER
   dequeue_options_visibility       IN  BINARY_INTEGER
   dequeue_options_wait             IN  BINARY_INTEGER
   dequeue_options_msgid            IN  RAW(16)
   dequeue_options_correlation      IN  VARCHAR2(128)
   dequeue_options_deq_condition    IN  VARCHAR2(4000)
   dequeue_options_transformation   IN  VARCHAR2(60)
   message_properties  			    OUT message_properties_t,
   payload             			    OUT "type_name",
   msgid               			    OUT RAW);