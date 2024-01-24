DROP TABLE IF EXISTS articles;
CREATE TABLE  articles (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        content TEXT NOT NULL ,
                        author VARCHAR(100),
                        publish_date DATE
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments (
                        id SERIAL PRIMARY KEY,
                        article_id INTEGER REFERENCES articles(id),
                        commenter_name VARCHAR(100),
                        comment_text TEXT,
                        comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
