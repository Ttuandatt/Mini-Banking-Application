# J-Banking Core System

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Java](https://img.shields.io/badge/java-21-orange)
![Spring Boot](https://img.shields.io/badge/spring--boot-3.2.0-green)
![Architecture](https://img.shields.io/badge/architecture-modular--monolith-blue)

## 1. Executive Summary
**J-Banking** is a high-performance, enterprise-grade Core Banking Engine designed to handle financial ledgers, transactional consistency, and double-entry bookkeeping principles.

Initially built as a **Modular Monolith**, the system is architected to ensure strict logical separation between business domains (Identity, Ledger, Transaction), paving the way for a seamless transition to a Distributed Microservices Architecture in the future.

**Key Design Philosophy:**
* **Zero-Trust Data Integrity:** ACID compliance is the highest priority.
* **Precision:** Usage of arbitrary-precision arithmetic (`BigDecimal`) for all monetary values.
* **Concurrency Safe:** Implements Optimistic Locking to handle high-concurrency race conditions.

---

## 2. System Architecture

### 2.1 High-Level Architecture (C4 Context)
The system currently operates as a self-contained unit exposing RESTful APIs, backed by a relational database for strong consistency.

[![](https://mermaid.ink/img/pako:eNplUdFugjAU_ZXmPm0JGiEowsMS0S1zwcRN9jLwodIrEqElBbI59d9XRNjM7ktvT8-557Q9QiQYggOxpPmO-LOQE1XvBcpgmibIS7IQmyRFMsnzNen1Hk7Pvr9ckbfHlX8inht4gjLi0pTyCOW6kXvuhfkk5CeV7FRrgxdF4vuEx2QqJCpiQy2qTWM95yVKTtOFYFWKRXNa15ypFEl5CNqGNJT1L2USRaLiZXBdiYcsRvmf50vKi626WtvcUpCzNpZKXF-hM79Frz63YDuzHdHFrQ9nbnC3FEUZS1y9evdXwzZvw2iwLloLgqY-J2HglLJCDTKUGa23cKwFIZQ7zDAER7WMyn0IIT8rTU75hxBZK5OiinfgbGlaqF2VM1riLKHq7bMOleoBUE7rSOCY9vAyBJwjfIGjj-2-pevm2LDt0cAw7bEGB3B6ltXXx9bQGNn60LIM0zxr8H3xHfQVPvhT-vkHjIrAsg?type=png)](https://mermaid.live/edit#pako:eNplUdFugjAU_ZXmPm0JGiEowsMS0S1zwcRN9jLwodIrEqElBbI59d9XRNjM7ktvT8-557Q9QiQYggOxpPmO-LOQE1XvBcpgmibIS7IQmyRFMsnzNen1Hk7Pvr9ckbfHlX8inht4gjLi0pTyCOW6kXvuhfkk5CeV7FRrgxdF4vuEx2QqJCpiQy2qTWM95yVKTtOFYFWKRXNa15ypFEl5CNqGNJT1L2USRaLiZXBdiYcsRvmf50vKi626WtvcUpCzNpZKXF-hM79Frz63YDuzHdHFrQ9nbnC3FEUZS1y9evdXwzZvw2iwLloLgqY-J2HglLJCDTKUGa23cKwFIZQ7zDAER7WMyn0IIT8rTU75hxBZK5OiinfgbGlaqF2VM1riLKHq7bMOleoBUE7rSOCY9vAyBJwjfIGjj-2-pevm2LDt0cAw7bEGB3B6ltXXx9bQGNn60LIM0zxr8H3xHfQVPvhT-vkHjIrAsg)

### 2.2 Project Structure (Package by Feature)

Unlike traditional Layered Architecture (Controller/Service/Repo), this project uses a **Package-by-Feature** approach to enforce modular boundaries.

```
com.fintech.jbanking
├── common              # Shared kernel (Utils, BaseEntities, Global Exceptions)
├── config              # Global configurations (Security, Swagger, Audit)
├── modules             # Distinct Business Domains
│   ├── identity        # Customer Management & Authentication
│   │   ├── api         # REST Controllers
│   │   ├── core        # Services & Domain Logic
│   │   └── data        # Repositories & Entities
│   ├── account         # General Ledger & Balance Management
│   └── transaction     # Money Movement Logic & Audit Logs
└── JBankingApplication.java

```

* * * * *

3\. Diagrams
--------------------

3.1\. Data Model (ERD)
--------------------

The database schema is normalized to 3NF (Third Normal Form) to ensure data integrity.

[![](https://mermaid.ink/img/pako:eNqNU11v2jAU_SuWnykQEjLIW5akW9QqIEJWaUJCrnOh0RKbOfZWGvjvs8NHKeOhfrsf555zbm4aTHkO2MMgwoKsBakWDOmXpdEsRbvd3d2uQX4QTLJkniIPLTD_y-oFPnSdC6aRN2g-85PUD-bxJDk011wJCoivbiJ2NxCSiDXII-JSS3MIzCs5W6MiR9OH91wtRaGzqgbBSAX_FVaqLJc3K7UkUtWa2sj4EfW--kkShSfB-yvdn5NBKOWKySVT1TMIlD3o8RkrfitAMcvh9TTdvBxoUZESPZOSML2sBU4pKcFDgw6aCl2sC848ZI0vQUceqoQARrdXov6AMCA9arKRRVXUsqDokdNfV64-bP9zzgSswFDCUtcPvrI4vOWHVGYF73nQy0ByuzEOw2g6SeN57ymefw9n_lOvVXIfzS4HtYDz15lGSRgn33ppFgRRmvbu_fgx-kCsnYJurzaICiAS8iU50u9xB69FkWNPCgUdXIGoiAlxa1pf3Qvoy8DmAHMi2jUZzIawn5xXJ5jgav2CvRUpax2pTa5Jjn_NOat3k4MIjHXsfelb7RDsNfgVe9bA7Y4te-z2XXtk28MO3mJvYHVdZ2i5Y8d2-6O-6zr7Dn5rWfvd0WBkDW3HcmxrMLKd_T8VNxci?type=png)](https://mermaid.live/edit#pako:eNqNU11v2jAU_SuWnykQEjLIW5akW9QqIEJWaUJCrnOh0RKbOfZWGvjvs8NHKeOhfrsf555zbm4aTHkO2MMgwoKsBakWDOmXpdEsRbvd3d2uQX4QTLJkniIPLTD_y-oFPnSdC6aRN2g-85PUD-bxJDk011wJCoivbiJ2NxCSiDXII-JSS3MIzCs5W6MiR9OH91wtRaGzqgbBSAX_FVaqLJc3K7UkUtWa2sj4EfW--kkShSfB-yvdn5NBKOWKySVT1TMIlD3o8RkrfitAMcvh9TTdvBxoUZESPZOSML2sBU4pKcFDgw6aCl2sC848ZI0vQUceqoQARrdXov6AMCA9arKRRVXUsqDokdNfV64-bP9zzgSswFDCUtcPvrI4vOWHVGYF73nQy0ByuzEOw2g6SeN57ymefw9n_lOvVXIfzS4HtYDz15lGSRgn33ppFgRRmvbu_fgx-kCsnYJurzaICiAS8iU50u9xB69FkWNPCgUdXIGoiAlxa1pf3Qvoy8DmAHMi2jUZzIawn5xXJ5jgav2CvRUpax2pTa5Jjn_NOat3k4MIjHXsfelb7RDsNfgVe9bA7Y4te-z2XXtk28MO3mJvYHVdZ2i5Y8d2-6O-6zr7Dn5rWfvd0WBkDW3HcmxrMLKd_T8VNxci)

* * * * *

3.2\. Class Diagram
--------------------

[![](https://mermaid.ink/img/pako:eNqtV-tv2zYQ_1cIAgXsNtbsuIltIQtgS-oq1I_AUlps8BdWOjvE9PBIKqmX5X8v9XJoWcqyzPxgUffi8e5-p_Mj9mIfsI69gHBuUrJhJFxFSK5379Cvr157lZ6GjMVstpijL9Zybk3fbCsiIfAt8QAZcRjGEXrM6enKnEUTwsGKBBU7lZeuq6sZ2W7Bd5ItsEz4-vpQ4sM0jjaI-kdUjwQmEeDSEJDHQG79sXhRKtn6VamnfCsf_yeU5xqyTWvu2u7vaLYwb6fWCYJp-5CFbBb7SQDHQb3lwKrh_OAIRmW4EslLLdVzISQ0qGetkyCYHyumZzmCiIQjnj1OHcG-hsaGsbidu6cL4Njz4iQSTfEr2E0hJDl7noTfgVVEJnRjgkdDEqDvJCCR1xBoL2EMIm9X4RYnNwT0uejvgXEaRxWWD9uYU9FSnCBhaq9dEXyg4s5n5OFFyX3ejgJTuHcEWIiSEBgR0rMqVseGa3-1DmnThfHFMg9pxnThqLRmJ5b5ZWNW0zloJICtZaaPWsaaRv5kN1Yz2MpTUo0RJ_fQKgRfFxVg99SDo6rJO1AhVFo0MqLpLqrHbkBM8sJp1ZVb-9Tw-qghdzmeO2mCZMM_GcRcRiJOvLQWmmCmiDRBjcEaUpyA7TcDLa_cCl8x7u62gIT8aRZ5CXBl_5OdcQmEq6irKYbquf8FJKZ1s3Bs95D4zXY_m8vxt0NqlrNP1vK1vrwBsjfW3LTnvx0SnVvDsBznkPhpbE__BbSKK28DbgZHxUq7FtjL53o5hnWTW1KlCbty9FjHLCzFWuVmCX8lwMUxfjv3JKDpLFGCuMqHH-Alsh2IOKSecp_WyZG9tKbjFNPOZ_vGebOhvaId3QGj4vmTpoxuV_90OtnUUc8pel49U4mBct8x57FHs7os8JgNNSvcW2H0XqqtcFfT3suX8mOto_ihlC1pubgqrfYcHfE4YR78IgiTbVc53YQtRHLI8ihw1JoknEYgq2Uab6jXPjiiLBxNu675MOnpuFX4VC21VKMeFarW3iWDxZx3ila692-HWoZMCpWzbHsvWj0pAvA5EjGSUsE-NkF6mZSaz76H40qds7XXy3TTMV5R3jtiyeZSk8nSA8WoozReNUWVILn7Jv6CUG4Ln-ENoz7WBUvgDMsmJ0db-YoziK-wuAM5y2Jdbn3C_lzhVfQkdbYk-iOOw1KNxcnmDutrEnD5lt-2-IO1p7I0FcxIr4H1weUoM4L1R_wD66OBNhwOLs673YtRvyd3Z3iH9c6gqw173e6w__G8P-j3z0dPZ_jv7Nie1u2dd4fdy1F3dDm86A_7Tz8BX_j1Lg?type=png)](https://mermaid.live/edit#pako:eNqtV-tv2zYQ_1cIAgXsNtbsuIltIQtgS-oq1I_AUlps8BdWOjvE9PBIKqmX5X8v9XJoWcqyzPxgUffi8e5-p_Mj9mIfsI69gHBuUrJhJFxFSK5379Cvr157lZ6GjMVstpijL9Zybk3fbCsiIfAt8QAZcRjGEXrM6enKnEUTwsGKBBU7lZeuq6sZ2W7Bd5ItsEz4-vpQ4sM0jjaI-kdUjwQmEeDSEJDHQG79sXhRKtn6VamnfCsf_yeU5xqyTWvu2u7vaLYwb6fWCYJp-5CFbBb7SQDHQb3lwKrh_OAIRmW4EslLLdVzISQ0qGetkyCYHyumZzmCiIQjnj1OHcG-hsaGsbidu6cL4Njz4iQSTfEr2E0hJDl7noTfgVVEJnRjgkdDEqDvJCCR1xBoL2EMIm9X4RYnNwT0uejvgXEaRxWWD9uYU9FSnCBhaq9dEXyg4s5n5OFFyX3ejgJTuHcEWIiSEBgR0rMqVseGa3-1DmnThfHFMg9pxnThqLRmJ5b5ZWNW0zloJICtZaaPWsaaRv5kN1Yz2MpTUo0RJ_fQKgRfFxVg99SDo6rJO1AhVFo0MqLpLqrHbkBM8sJp1ZVb-9Tw-qghdzmeO2mCZMM_GcRcRiJOvLQWmmCmiDRBjcEaUpyA7TcDLa_cCl8x7u62gIT8aRZ5CXBl_5OdcQmEq6irKYbquf8FJKZ1s3Bs95D4zXY_m8vxt0NqlrNP1vK1vrwBsjfW3LTnvx0SnVvDsBznkPhpbE__BbSKK28DbgZHxUq7FtjL53o5hnWTW1KlCbty9FjHLCzFWuVmCX8lwMUxfjv3JKDpLFGCuMqHH-Alsh2IOKSecp_WyZG9tKbjFNPOZ_vGebOhvaId3QGj4vmTpoxuV_90OtnUUc8pel49U4mBct8x57FHs7os8JgNNSvcW2H0XqqtcFfT3suX8mOto_ihlC1pubgqrfYcHfE4YR78IgiTbVc53YQtRHLI8ihw1JoknEYgq2Uab6jXPjiiLBxNu675MOnpuFX4VC21VKMeFarW3iWDxZx3ila692-HWoZMCpWzbHsvWj0pAvA5EjGSUsE-NkF6mZSaz76H40qds7XXy3TTMV5R3jtiyeZSk8nSA8WoozReNUWVILn7Jv6CUG4Ln-ENoz7WBUvgDMsmJ0db-YoziK-wuAM5y2Jdbn3C_lzhVfQkdbYk-iOOw1KNxcnmDutrEnD5lt-2-IO1p7I0FcxIr4H1weUoM4L1R_wD66OBNhwOLs673YtRvyd3Z3iH9c6gqw173e6w__G8P-j3z0dPZ_jv7Nie1u2dd4fdy1F3dDm86A_7Tz8BX_j1Lg)


3.3\. Sequence Diagram - Core Business Flow (Money Transfer)
--------------------
The following sequence illustrates the **atomic transaction** process when User A transfers money to User B. Note the usage of Database Locking to ensure consistency.

[![](https://mermaid.ink/img/pako:eNqlVWtvmzAU_SuWPxGNMPJsYmnVIA8JrSVZoJo0RZo8uKSoYKe2E62r-t9nAk3Skr40PliA7zn33sO55h5HPAZMsITbDbAIxildCZovGdLXmgqVRumaMoVGWQpM1d-HgjKZgBhxpgTPMhAvxwQgtmkE9QAniviGqQWsuUwVF3f1kLGLqERzLtVKQPD9YsnKmLKu5vl5vRCC5rMgRJ9VtSORkQiee7GJFC9WmhdZGyVRHX9EWlVO0BpEwkX--N4QcFvhy5VGKt1SBad7Llef632-BfE8yNRdEhSEziJE4cLxA2cUejMfGV93gQU3ZzRrPPb-DK7rrQlJUJKy2L3z4qr3qtpaoAbvkk8uJqMQWZaFprMFupqPnXCCjDlImeap1J8D_RCprv-CRzcV19htnpKqSoEcZBTBEL9Sdw28pVkaax1dmlFtS-OpyJlC1QbymNwkiXbJ3p2nMugUpVEImgjBtTU8P7iaTr2RN_HDX9MrfxyUaMgk7Mln316mfF3swmCNA_hDch9gbyjr1pR9p7qO9bvqsPmlmoIP4d09_tMB_0GpJN2CsfdI43_Q7hH6NZ5Ccf3dJ3q8PD-cIXWYKnmK4Y1BHc0uL70nk_qeMo6s2LZtbTFkHE23N656ARaXNzG8dKZgE69EGmOixAZMnIPIafGI7wvkEqtryGGJib6NqbhZ4iV70Bh9nP7kPH-ECb5ZXWOSUG18E2_WxdRVf4F9iC6mOBi11picDXo7Dkzu8R9MWp2e1Wn1ex1bb5ydte22ie8waXYte9gbdFq91tDuD7qt9oOJ_-7S2tagP7B7fbs9aHX7nWF3-PAPXEAgjQ?type=png)](https://mermaid.live/edit#pako:eNqlVWtvmzAU_SuWPxGNMPJsYmnVIA8JrSVZoJo0RZo8uKSoYKe2E62r-t9nAk3Skr40PliA7zn33sO55h5HPAZMsITbDbAIxildCZovGdLXmgqVRumaMoVGWQpM1d-HgjKZgBhxpgTPMhAvxwQgtmkE9QAniviGqQWsuUwVF3f1kLGLqERzLtVKQPD9YsnKmLKu5vl5vRCC5rMgRJ9VtSORkQiee7GJFC9WmhdZGyVRHX9EWlVO0BpEwkX--N4QcFvhy5VGKt1SBad7Llef632-BfE8yNRdEhSEziJE4cLxA2cUejMfGV93gQU3ZzRrPPb-DK7rrQlJUJKy2L3z4qr3qtpaoAbvkk8uJqMQWZaFprMFupqPnXCCjDlImeap1J8D_RCprv-CRzcV19htnpKqSoEcZBTBEL9Sdw28pVkaax1dmlFtS-OpyJlC1QbymNwkiXbJ3p2nMugUpVEImgjBtTU8P7iaTr2RN_HDX9MrfxyUaMgk7Mln316mfF3swmCNA_hDch9gbyjr1pR9p7qO9bvqsPmlmoIP4d09_tMB_0GpJN2CsfdI43_Q7hH6NZ5Ccf3dJ3q8PD-cIXWYKnmK4Y1BHc0uL70nk_qeMo6s2LZtbTFkHE23N656ARaXNzG8dKZgE69EGmOixAZMnIPIafGI7wvkEqtryGGJib6NqbhZ4iV70Bh9nP7kPH-ECb5ZXWOSUG18E2_WxdRVf4F9iC6mOBi11picDXo7Dkzu8R9MWp2e1Wn1ex1bb5ydte22ie8waXYte9gbdFq91tDuD7qt9oOJ_-7S2tagP7B7fbs9aHX7nWF3-PAPXEAgjQ)


4\. Key API Specifications
--------------------------

Full API documentation is available via **Swagger UI** at `http://localhost:8080/swagger-ui.html` (when the app is running).

| **Method** | **Endpoint** | **Description** | **Request Body (Example)** |
| --- | --- | --- | --- |
| `POST` | `/api/v1/accounts` | Open a new account | `{ "ownerName": "Alex", "initialBalance": 1000 }` |
| `GET` | `/api/v1/accounts/{id}` | Get account balance | - |
| `POST` | `/api/v1/transfers` | Execute money transfer | `{ "sourceId": 1, "targetId": 2, "amount": 500 }` |
| `GET` | `/api/v1/transfers/{refId}` | Get transaction status |  |

5\. Technology Stack
--------------------

### Core Framework

| **Component** | **Technology** | **Version** | **Rationale** |
| --- | --- | --- | --- |
| **Language** | Java | 25 (LTS) | Virtual Threads support, Pattern Matching. |
| **Framework** | Spring Boot | 4.0.1 | Rapid development, strong ecosystem support. |
| **Build Tool** | Maven | 4.0.0 | Standard dependency management. |

### Data & Persistence

| **Component** | **Technology** | **Rationale** |
| --- | --- | --- |
| **Database** | PostgreSQL | 15+ |
| **ORM** | Spring Data JPA | - |
| **Migration** | Flyway (Planned) | - |

### Quality & Utility

-   **Lombok:** Boilerplate code reduction.

-   **Resilience4j:** Circuit breaker (prepared for future Microservices integration).

-   **JUnit 5 & Mockito:** Unit and Integration testing.

-   **Docker:** Containerization for consistent deployment.

* * * * *

6\. Critical Design Decisions
-----------------------------

### 6.1 Handling Concurrency

To prevent the "Lost Update Problem" (e.g., two concurrent withdrawals causing a negative balance), we utilize **Optimistic Locking** via the JPA `@Version` annotation.

-   **Mechanism:** Every update to the `accounts` table checks if the version matches. If a conflict is detected, an `OptimisticLockingFailureException` is thrown, and the transaction is rolled back.

### 6.2 Monetary Calculations

Floating-point arithmetic (float/double) is strictly forbidden.

-   **Implementation:** All monetary values use `java.math.BigDecimal`.

-   **Database:** Stored as `DECIMAL(19, 2)` to support values up to 99 quadrillion with 2 decimal places precision.

### 6.3 Modular Monolith Strategy

We chose Modular Monolith over Microservices for the initial phase to:

1.  Reduce operational complexity (No network latency, simplified deployment).

2.  Refactor easily (IDE support) before boundaries are solidified.

3.  **Future Proofing:** Code is organized such that extracting `Account Module` to a separate service requires minimal effort.

* * * * *

7\. Setup & Installation
------------------------

### Prerequisites

-   Java JDK 21

-   Maven 3.x

-   PostgreSQL running on port 5432

### Running Locally

1.  Clone the repository:

    Bash

    ```
    git clone https://github.com/Ttuandatt/Mini-Banking-Application.git

    ```

2.  Configure Database in `application.properties`:

    Properties

    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/jbanking_db
    spring.datasource.username=postgres
    spring.datasource.password=your_password

    ```

3.  Build and Run:

    Bash

    ```
    mvn clean install
    mvn spring-boot:run

    ```

* * * * *

8\. Future Roadmap
------------------

-   [ ] **Phase 2:** Implement JWT Authentication (Spring Security).

-   [ ] **Phase 3:** Integrate Kafka for asynchronous notifications.

-   [ ] **Phase 4 (Migration):** Extract `Transaction Service` into a standalone Microservice using Spring Cloud.

* * * * *

*Document maintained by Phan Canh Tuan Dat*

```

***

### Hướng dẫn sử dụng file này hiệu quả

1.  **Vị trí:** Đặt file này ngang hàng với `README.md`.
2.  **README vs OVERVIEW:**
    * `README.md`: Thường ngắn gọn, hướng dẫn cách chạy (Run) nhanh cho người mới clone về.
    * `OVERVIEW.md`: Chuyên sâu hơn về kiến trúc và thiết kế (như nội dung trên).
3.  **Điểm nhấn:** Phần **"Critical Design Decisions"** là phần "ăn tiền" nhất. Nó chứng minh cho người đọc thấy bạn code có tư duy, có lý do (Rationale), chứ không phải code theo tutorial trên mạng.

Bạn có thể copy nội dung trên và chỉnh sửa lại các thông tin cá nhân (Your Name, GitHub Link) nhé. Bạn có muốn tôi giải thích sâu hơn về phần nào trong file này không?

```