# OS Experiments in Kotlin

OS에 대한 개념을 Kotlin으로 재현하기 위함. <br />
메모리, 캐시, 스케줄링, 동시성, 교착상태, 생산자-소비자, 경쟁상태 등 핵심 개념을 재현

## Category

### 1. Memory & Cache
- Locality -> Row vs Column 순회 (캐시 히트의 중요성)
- RandomAccess -> 순차 vs 랜덤 접근
- ArrayVsLinkedList -> 연속 vs 비연속 메모리 비교
- FalseSharing -> 캐시 라인 충돌

### 2. Process & Thread
- ThreadVsCoroutine -> Task가 많을 때 Thread와 Coroutine 차이를 비교
- RaceCondition -> 경쟁상태, 임계영역 보호, 동기화 등을 실험 즉, 공유 자원에 대해 발생하는 문제점을 실험
- Deadlock -> 교착 상태를 재현
- ProducerConsumer -> 생산자-소비자 실험(세마포어 대신 ArrayBlockingQueue 사용)
- Semaphore -> 세마포어 실험

### 3. File & I/O
- FileVsMemory -> 디스크와 메모리 비교

## Notes
- 시스템 환경에 따라 결과가 달라집니다.
- 측정 시간은 나노타임으로 계산.
- 계속된 추가 또는 수정 예정.
