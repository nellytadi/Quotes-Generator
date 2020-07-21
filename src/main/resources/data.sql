INSERT INTO tag (tag_id, tag, created_at,updated_at) VALUES (nextval('tag_seq'), 'business',current_timestamp,current_timestamp); 
INSERT INTO tag (tag_id, tag, created_at,updated_at) VALUES (nextval('tag_seq'), 'food',current_timestamp,current_timestamp); 
INSERT INTO tag (tag_id, tag, created_at,updated_at) VALUES (nextval('tag_seq'), 'emotional',current_timestamp,current_timestamp); 
INSERT INTO tag (tag_id, tag, created_at,updated_at) VALUES (nextval('tag_seq'), 'immigration',current_timestamp,current_timestamp); 
INSERT INTO tag (tag_id, tag, created_at,updated_at) VALUES (nextval('tag_seq'), 'inspiration',current_timestamp,current_timestamp); 
INSERT INTO tag (tag_id, tag, created_at,updated_at) VALUES (nextval('tag_seq'), 'politics',current_timestamp,current_timestamp); 


INSERT INTO quote (quote_id, quote, author, created_at,updated_at) VALUES (nextval('quote_seq'), 'You’ve gotta dance like there’s nobody watching, love like you’ll never be hurt, sing like there’s nobody listening, and live like it’s heaven on earth.','William W. Purkey',current_timestamp,current_timestamp); 
INSERT INTO quote (quote_id, quote, author, created_at,updated_at) VALUES (nextval('quote_seq'), 'Fairy tales are more than true: not because they tell us that dragons exist, but because they tell us that dragons can be beaten.','Neil Gaiman',current_timestamp,current_timestamp); 
INSERT INTO quote (quote_id, quote, author, created_at,updated_at) VALUES (nextval('quote_seq'), 'Everything you can imagine is real','Pablo Picasso',current_timestamp,current_timestamp); 
INSERT INTO quote (quote_id, quote, author, created_at,updated_at) VALUES (nextval('quote_seq'), 'When one door of happiness closes, another opens; but often we look so long at the closed door that we do not see the one which has been opened for us.','Helen Keller',current_timestamp,current_timestamp); 
INSERT INTO quote (quote_id, quote, author, created_at,updated_at) VALUES (nextval('quote_seq'), 'Do one thing every day that scares you.','Eleanor Roosevelt',current_timestamp,current_timestamp); 
INSERT INTO quote (quote_id, quote, author, created_at,updated_at) VALUES (nextval('quote_seq'), 'Smart people learn from everything and everyone, average people from their experiences, stupid people already have all the answers.','Socrates',current_timestamp,current_timestamp); 

insert into quote_tag (quote_id, tag_id) (select q.quote_id, t.tag_id from quote q, tag t where q.author ='William W. Purkey' AND t.tag = 'immigration');
insert into quote_tag (quote_id, tag_id) (select q.quote_id, t.tag_id from quote q, tag t where q.author ='William W. Purkey' AND t.tag = 'inspiration');
insert into quote_tag (quote_id, tag_id) (select q.quote_id, t.tag_id from quote q, tag t where q.author ='Neil Gaiman' AND t.tag = 'politics');
insert into quote_tag (quote_id, tag_id) (select q.quote_id, t.tag_id from quote q, tag t where q.author ='Pablo Picasso' AND t.tag = 'politics');
insert into quote_tag (quote_id, tag_id) (select q.quote_id, t.tag_id from quote q, tag t where q.author ='Helen Keller' AND t.tag = 'politics');
insert into quote_tag (quote_id, tag_id) (select q.quote_id, t.tag_id from quote q, tag t where q.author ='Eleanor Roosevelt' AND t.tag = 'inspiration');
insert into quote_tag (quote_id, tag_id) (select q.quote_id, t.tag_id from quote q, tag t where q.author ='Socrates' AND t.tag = 'politics');
insert into quote_tag (quote_id, tag_id) (select q.quote_id, t.tag_id from quote q, tag t where q.author ='Neil Gaiman' AND t.tag = 'emotional');