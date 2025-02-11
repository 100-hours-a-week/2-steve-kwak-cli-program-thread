기존의 동기 프로그램은 사용자가 메뉴를 선택하고, 맵기와 재료를 선택한 후, 주문과 결제를 순차적으로 처리했습니다. 이 과정에서 각각의 작업이 끝날 때까지 기다린 후 다음 작업을 처리하는 방식이었습니다.
비동기 처리로 변경된 부분은 주문 처리와 결제 처리를 동시에 진행할 수 있도록 만든 점입니다. 비동기 처리를 위해 CompletableFuture와 ExecutorService를 사용했습니다.

ExecutorService를 사용하여 스레드 풀을 생성합니다. 이를 통해 여러 작업을 동시에 처리할 수 있습니다.
CompletableFuture를 사용하여 비동기 작업을 처리합니다. runAsync 메서드를 사용하여 비동기적으로 작업을 실행하고, 후속 작업은 thenRunAsync를 사용하여 연결합니다.

동기 코드에서는 주문 처리와 결제 처리 순차적으로 진행되었으므로, 사용자가 주문 처리와 결제 처리에 대해 각각 기다려야 했습니다.
비동기 코드에서는 주문과 결제 처리가 동시에 진행되며, 한 작업이 완료될 때까지 대기할 필요 없이 다른 작업을 처리할 수 있게 되었습니다.
이 방식은 시스템의 응답성을 개선하고, 대기 시간을 단축시켰습니다.

