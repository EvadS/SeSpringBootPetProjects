 removedCounter = new MultiTaggedCounter("counter_name",
                meterRegistry,
                new ArrayList<String>());
        removedCounter.increment(NAME_1,new ArrayList<String>());

        removedCounter2 = new MultiTaggedCounter("counter_name",
                meterRegistry,
                Arrays.asList("tag1"));
        removedCounter2.increment(NAME_1, Arrays.asList("tag1_name"));
        log.info("created counter: {} ", removedCounter);
        
        public void increment(String counterName, List<String> tagValues) {
        // ["1", "test", "state"]
        //        String valuesString = Arrays.toString(tagValues);
        //
        //        if (tagValues.length != tagNames.size()) {
        //            throw new IllegalArgumentException("Counter tags mismatch! ");//Expected args are " + Arrays.toString(tagNames) + ", provided tags are " + valuesString);
        //        }
        
                Counter counter = counters.get(counterName);
        
                if (counter == null) {
        
                    List<Tag> tags = new ArrayList<>(tagNames.size());
        
                    for (int i = 0; i < tagNames.size(); i++) {
        
                        tags.add(new ImmutableTag(tagNames.get(i), tagValues.get(i)));
                    }        
                    counter = Counter.builder(name)
                            .tags(tags)
                            .register(registry);
        
                    counters.put(counterName, counter);
                }        
                counter.increment();
            }
            
 каунтер будет только один поскольку имя уникаьно на уровне прометеуса