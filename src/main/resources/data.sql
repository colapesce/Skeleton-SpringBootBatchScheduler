insert into config_job values (1, to_date('20180918', 'yyyymmdd'), 'lottoJob', null);
insert into CONFIG_STEP values (1, '{ "reader": { "className":"CSVFileReaderBuilder", "info": { "mapperClass":"EstrazioneFieldSetMapper", "resource":"fileRepos/inputs/storico.txt", "tokenNames":["date","ruota","column1","column2","column3","column4","column5"], "delimiter":"\t" } }, "processor": { "className":"EstrazioneItemProcessorBuilder", "info":{} }, "writer": { "className":"JPAWriterBuilder", "info":{} }, "chunk":"500" }', 1, to_date('20180918', 'yyyymmdd'), 'step1', 1);
insert into CONFIG_STEP values (2, '{ "reader": { "className":"DBReaderBuilder", "info": { "mapperClass":"RuotaFieldSetMapper", "query":"SELECT IDRUOTA ID, MIN(DATE) FIRSTUSE FROM ESTRAZIONI GROUP BY IDRUOTA" } }, "processor": { "className":"RuoteItemProcessorBuilder", "info":{} }, "writer": { "className":"JPAWriterBuilder", "info":{} }, "chunk":"10" }', 2, to_date('20180918', 'yyyymmdd'), 'step2', 1);