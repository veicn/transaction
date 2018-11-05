ALTER TABLE `members`
  ADD COLUMN `security_question1` VARCHAR(200) NULL   COMMENT '密保问题1' AFTER `updater`,
  ADD COLUMN `security_answer1` VARCHAR(200) NULL   COMMENT '密保答案1' AFTER `security_question1`,
  ADD COLUMN `security_question2` VARCHAR(200) NULL   COMMENT '密保问题2' AFTER `security_answer1`,
  ADD COLUMN `security_answer2` VARCHAR(200) NULL   COMMENT '密保答案2' AFTER `security_question2`,
  ADD COLUMN `security_question3` VARCHAR(200) NULL   COMMENT '密保问题3' AFTER `security_answer2`,
  ADD COLUMN `security_answer3` VARCHAR(200) NULL   COMMENT '密保答案4' AFTER `security_question3`;
