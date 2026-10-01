INSERT IGNORE INTO menus (id, name, description, price, created_at, updated_at) VALUES
       (1, '아이스 아메리카노', '상큼한 에티오피아 원두 베이스', 4500, NOW(), NOW()),
       (2, '카페라떼', '1등급 원유와 에스프레소의 조화', 5000, NOW(), NOW()),
       (3, '카페모카', '진한 벨기에 초콜릿과 에스프레소', 5500, NOW(), NOW()),
       (4, '바닐라 라떼', '향긋한 바닐라 시럽 추가', 5500, NOW(), NOW()),
       (5, '카푸치노', '1등급 원유의 부드러운 거품', 5000, NOW(), NOW()),
       (6, '콜드브루', '저온 추출 최고급 원두', 4800, NOW(), NOW());

INSERT IGNORE INTO users(id, point, created_at, updated_at) VALUES
       (1, 20000, NOW(), NOW()),
       (2, 1000, NOW(), NOW());