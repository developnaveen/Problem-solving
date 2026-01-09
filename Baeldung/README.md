

#thread 

## topics 

            ┌──────────────┐
            │  CPU + RAM   │
            └──────┬───────┘
                   │
                   ▼
              ┌─────────────────────┐
              | Java Memory Model   │
              │ (JMM)               │
              └──────┬──────────────┘
                     │
                     ▼
             ┌──────────────────────────┐
             | Thread & Visibility      │
             │ volatile, happens-before │
             └──────┬───────────────────┘
                    │
                    ▼
             ┌────────────────────────────┐
             │ synchronized, locks        │
             │ race conditions            │
             └──────┬─────────────────────┘
                    │
                    ▼
             ┌────────────────────────────┐
             │ wait(), notify(), join()   │
             │ thread communication       │
             └──────┬─────────────────────┘
                    │
                    ▼
            ┌──────────────────────────────┐
            │ Thread Pools                 │
            │ Executor, ExecutorService    │
            └──────┬───────────────────────┘
                   │
                   ▼
            ┌──────────────────────────────┐
            │ Task Scheduling              │
            │ ScheduledExecutorService     │
            └──────┬───────────────────────┘
                   │
                   ▼
            ┌──────────────────────────────┐
            │ Futures                      │
            │ Future, ScheduledFuture      │
            └──────┬───────────────────────┘
                   │
                   ▼
            ┌──────────────────────────────┐
            │ CompletableFuture            │
            │ thenApply, thenCompose, etc  │
            └──────┬───────────────────────┘
                   │
                   ▼
            ┌──────────────────────────────┐
            │ Parallel Pipelines           │
            │ allOf(), anyOf(), combining  │
            └──────┬───────────────────────┘
                   │
                   ▼
            ┌──────────────────────────────┐
            │ Error Handling               │
            │ exceptionally(), handle()    │
            └──────┬───────────────────────┘
                   │
                   ▼
            ┌──────────────────────────────┐
            │ ForkJoin vs Executor vs CF   │
            │ Performance decisions        │
            └──────┬───────────────────────┘
                   │
                   ▼
            ┌──────────────────────────────┐
            │ Servlet Async Model          │
            │ AsyncContext, non-blocking   │
            └──────┬───────────────────────┘
                   │
                   ▼
            ┌──────────────────────────────┐
            │ Production Patterns          │
            │ Email, PDF, API calls, DB    │
            └──────────────────────────────┘
 
