DELETE FROM EMERGENCY_VEHICLE;
DELETE FROM TRAFFIC_SIGNALS;
DELETE FROM CONGESTION_DATA;

INSERT INTO TRAFFIC_SIGNALS
(JUNCTION_NAME, STATUS, VEHICLE_COUNT, CONGESTION_LEVEL, LATITUDE, LONGITUDE, MANUAL_OVERRIDE)
VALUES
('Shivajinagar Junction','GREEN',120,45.5,18.5308,73.8475,false),
('FC Road Junction','GREEN',150,40,18.5220,73.8550,false),
('JM Road Junction','RED',180,70,18.5255,73.8500,false),
('Camp Signal','GREEN',130,35,18.5018,73.8636,false),
('Swargate Signal','RED',250,90,18.4974,73.8501,false),
('Pune Station Signal','GREEN',170,50,18.5289,73.8742,false),
('Shaniwarwada Signal','RED',220,80,18.5204,73.8567,false),
('Deccan Signal','GREEN',160,45,18.5167,73.8562,false),
('Karve Road Signal','RED',190,75,18.5074,73.8077,false),
('Kothrud Signal','GREEN',140,38,18.5010,73.8000,false),
('Aundh Signal','RED',210,82,18.5590,73.8070,false),
('Baner Signal','GREEN',155,42,18.5679,73.7797,false),
('Wakad Signal','RED',240,88,18.5975,73.7898,false),
('Hinjewadi Signal','GREEN',175,48,18.5912,73.7389,false),
('Nigdi Signal','RED',200,78,18.6513,73.7706,false),
('Pimpri Signal','GREEN',165,44,18.6298,73.7997,false),
('Chinchwad Signal','RED',195,76,18.6270,73.7820,false),
('Yerawada Signal','GREEN',145,39,18.5510,73.8950,false),
('Kharadi Signal','RED',225,85,18.5519,73.9476,false),
('Viman Nagar Signal','GREEN',160,46,18.5679,73.9143,false),
('Magarpatta Signal','RED',230,87,18.5150,73.9250,false),
('Warje Signal','GREEN',135,36,18.4897,73.7980,false),
('Karve Nagar Signal','RED',185,72,18.4890,73.8210,false),
('Bibwewadi Signal','GREEN',150,40,18.4768,73.8660,false),
('Dhankawadi Signal','RED',210,83,18.4630,73.8520,false),
('Sinhagad Road Signal','GREEN',170,50,18.4700,73.8200,false),
('Market Yard Signal','RED',240,90,18.4870,73.8690,false),
('Hadapsar Signal','GREEN',180,52,18.5089,73.9260,false),
('Kondhwa Signal','RED',205,79,18.4730,73.8940,false),
('Wanowrie Signal','GREEN',150,41,18.4980,73.8970,false),
('Fatima Nagar Signal','RED',195,74,18.5070,73.8990,false),
('Pashan Signal','GREEN',140,35,18.5350,73.7980,false),
('Bavdhan Signal','RED',190,73,18.5200,73.7800,false),
('Balewadi Signal','GREEN',160,47,18.5760,73.7890,false),
('University Circle','RED',220,84,18.5390,73.8300,false),
('Model Colony Signal','GREEN',145,38,18.5340,73.8370,false),
('Nal Stop Signal','RED',200,80,18.5130,73.8330,false),
('Dandekar Bridge Signal','GREEN',155,42,18.5030,73.8420,false),
('Alka Talkies Signal','RED',180,69,18.5150,73.8490,false),
('Tilak Road Signal','GREEN',160,46,18.5080,73.8490,false),
('RTO Signal','RED',210,82,18.5310,73.8680,false),
('Bund Garden Signal','GREEN',170,49,18.5390,73.8820,false),
('Sangamwadi Signal','RED',225,86,18.5440,73.8680,false),
('Koregaon Park Signal','GREEN',150,40,18.5380,73.8920,false),
('Mundhwa Signal','RED',230,88,18.5330,73.9200,false),
('Phursungi Signal','GREEN',145,39,18.4790,73.9620,false),
('Bhosari Signal','RED',215,84,18.6210,73.8450,false),
('Alandi Road Signal','GREEN',165,44,18.6000,73.8680,false),
('Dighi Signal','RED',205,81,18.6080,73.8600,false),
('Lohegaon Signal','GREEN',155,42,18.5910,73.9100,false);

INSERT INTO CONGESTION_DATA
(LOCATION, VEHICLE_COUNT, CONGESTION_SCORE, CONGESTION_LEVEL)
VALUES
('Shivajinagar',320,82.5,'HIGH'),
('FC Road',350,88.5,'HIGH'),
('JM Road',280,70.5,'MEDIUM'),
('Camp',240,65.0,'MEDIUM'),
('Swargate',420,95.5,'HIGH'),
('Pune Station',300,78.0,'HIGH'),
('Deccan',260,68.0,'MEDIUM'),
('Kothrud',220,58.0,'MEDIUM'),
('Aundh',250,63.5,'MEDIUM'),
('Baner',210,55.0,'LOW'),
('Wakad',340,84.0,'HIGH'),
('Hinjewadi',390,92.0,'HIGH'),
('Nigdi',240,61.0,'MEDIUM'),
('Pimpri',280,74.0,'HIGH'),
('Chinchwad',270,72.0,'HIGH'),
('Yerawada',230,60.0,'MEDIUM'),
('Kharadi',360,89.0,'HIGH'),
('Viman Nagar',290,76.0,'HIGH'),
('Magarpatta',310,80.0,'HIGH'),
('Hadapsar',370,91.0,'HIGH');


INSERT INTO EMERGENCY_VEHICLE
(VEHICLE_NUMBER, TYPE, LOCATION, DESTINATION, PRIORITY, LATITUDE, LONGITUDE, ROUTE_CLEARED)
VALUES
('AMB-101','Ambulance','Shivajinagar','Ruby Hall Clinic','HIGH',18.5308,73.8475,true),

('AMB-102','Ambulance','FC Road','Ruby Hall Clinic','HIGH',18.5220,73.8550,true),

('AMB-103','Ambulance','JM Road','Jehangir Hospital','HIGH',18.5255,73.8500,true),

('AMB-104','Ambulance','Camp','City Hospital','HIGH',18.5018,73.8636,true),

('AMB-105','Ambulance','Swargate','KEM Hospital','HIGH',18.4974,73.8501,true),

('AMB-106','Ambulance','Pune Station','Sassoon Hospital','HIGH',18.5289,73.8742,true),

('AMB-107','Ambulance','Deccan','Ruby Hall Clinic','HIGH',18.5167,73.8562,true),

('AMB-108','Ambulance','Aundh','City Hospital','HIGH',18.5590,73.8070,true),

('AMB-109','Ambulance','Baner','Jehangir Hospital','HIGH',18.5679,73.7797,true),

('AMB-110','Ambulance','Kothrud','Ruby Hall Clinic','HIGH',18.5074,73.8077,true),

('AMB-111','Ambulance','Hadapsar','City Hospital','HIGH',18.5089,73.9260,true),

('AMB-112','Ambulance','Wakad','Ruby Hall Clinic','HIGH',18.5975,73.7898,true),

('AMB-113','Ambulance','Hinjewadi','Jehangir Hospital','HIGH',18.5912,73.7389,true),

('AMB-114','Ambulance','Nigdi','KEM Hospital','HIGH',18.6513,73.7706,true),

('AMB-115','Ambulance','Pimpri','Sassoon Hospital','HIGH',18.6298,73.7997,true),

('AMB-116','Ambulance','Chinchwad','Ruby Hall Clinic','HIGH',18.6270,73.7820,true),

('AMB-117','Ambulance','Yerawada','Jehangir Hospital','HIGH',18.5510,73.8950,true),

('AMB-118','Ambulance','Kharadi','Ruby Hall Clinic','HIGH',18.5519,73.9476,true),

('AMB-119','Ambulance','Viman Nagar','Jehangir Hospital','HIGH',18.5679,73.9143,true),

('AMB-120','Ambulance','Magarpatta','City Hospital','HIGH',18.5150,73.9250,true),

('AMB-121','Ambulance','Warje','KEM Hospital','HIGH',18.4897,73.7980,true),

('AMB-122','Ambulance','Karve Nagar','Ruby Hall Clinic','HIGH',18.4890,73.8210,true),

('AMB-123','Ambulance','Bibwewadi','Sassoon Hospital','HIGH',18.4768,73.8660,true),

('AMB-124','Ambulance','Dhankawadi','KEM Hospital','HIGH',18.4630,73.8520,true),

('AMB-125','Ambulance','Sinhagad Road','Ruby Hall Clinic','HIGH',18.4700,73.8200,true);