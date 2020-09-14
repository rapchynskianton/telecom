CREATE TABLE "user_cabinet" (
	"id" serial NOT NULL,
	"activation_date" TIMESTAMP NOT NULL,
	"status" BOOLEAN NOT NULL,
	"user_account_id" integer NOT NULL,
	"branch_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_cabinet_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "branch" (
	"id" serial NOT NULL,
	"region" character varying(20) NOT NULL,
	"adress" character varying(50) NOT NULL,
	"telephone" character varying(20) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT branch_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "connected_tariff_plan" (
	"id" serial NOT NULL,
	"user_cabinet_id" integer NOT NULL,
	"tariff_plan_id" integer NOT NULL,
	"activation_date" TIMESTAMP NOT NULL,
	"sum_cost" DECIMAL NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT connected_tariff_plan_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "tariff_plan" (
	"id" serial NOT NULL,
	"name" character varying(20) NOT NULL,
	"cost_per_unit" DECIMAL NOT NULL,
	"unit" integer NOT NULL,
	"type" character varying(20) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT tariff_plan_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "tariff_plan_2_attribute_value" (
	"tariff_plan_id" integer NOT NULL,
	"attribute_value_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "attribute_value" (
	"id" serial NOT NULL,
	"attribute_id" integer NOT NULL,
	"value" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT attribute_value_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "attribute" (
	"id" serial NOT NULL,
	"name" character varying(20) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT attribute_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "user_account" (
	"id" serial NOT NULL,
	"email" character varying(50) NOT NULL,
	"user_password" character varying(50) NOT NULL,
	"role" character varying(20) NOT NULL,
	"first_name" character varying(20) NOT NULL,
	"last_name" character varying(20) NOT NULL,
	"fathers_name" character varying(20) NOT NULL,
	"adress" character varying(20) NOT NULL,
	"telephone" character varying(20) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT user_account_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "passport_details" (
	"id" integer NOT NULL,
	"serial" character varying(2) NOT NULL,
	"serial_number" character varying(7) NOT NULL,
	"date_of_issue" TIMESTAMP NOT NULL,
	"identification_number" character varying(14) NOT NULL,
	"passport_authority" character varying(30) NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT passport_details_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "support" (
	"id" serial NOT NULL,
	"problem_name" character varying(20) NOT NULL,
	"problem" TEXT NOT NULL,
	"status" BOOLEAN NOT NULL,
	"user_cabinet_id" integer NOT NULL,
	"user_account_id" integer NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT support_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "transaction" (
	"id" serial NOT NULL,
	"value" DECIMAL NOT NULL,
	"user_cabinet_id" integer NOT NULL,
	"description" TEXT NOT NULL,
	"created" TIMESTAMP NOT NULL,
	"updated" TIMESTAMP NOT NULL,
	CONSTRAINT transaction_pk PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "user_cabinet" ADD CONSTRAINT "user_cabinet_fk0" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");
ALTER TABLE "user_cabinet" ADD CONSTRAINT "user_cabinet_fk1" FOREIGN KEY ("branch_id") REFERENCES "branch"("id");


ALTER TABLE "connected_tariff_plan" ADD CONSTRAINT "connected_tariff_plan_fk0" FOREIGN KEY ("user_cabinet_id") REFERENCES "user_cabinet"("id");
ALTER TABLE "connected_tariff_plan" ADD CONSTRAINT "connected_tariff_plan_fk1" FOREIGN KEY ("tariff_plan_id") REFERENCES "tariff_plan"("id");


ALTER TABLE "tariff_plan_2_attribute_value" ADD CONSTRAINT "tariff_plan_2_attribute_value_fk0" FOREIGN KEY ("tariff_plan_id") REFERENCES "tariff_plan"("id");
ALTER TABLE "tariff_plan_2_attribute_value" ADD CONSTRAINT "tariff_plan_2_attribute_value_fk1" FOREIGN KEY ("attribute_value_id") REFERENCES "attribute_value"("id");

ALTER TABLE "attribute_value" ADD CONSTRAINT "attribute_value_fk0" FOREIGN KEY ("attribute_id") REFERENCES "attribute"("id");



ALTER TABLE "passport_details" ADD CONSTRAINT "passport_details_fk0" FOREIGN KEY ("id") REFERENCES "user_account"("id");

ALTER TABLE "support" ADD CONSTRAINT "support_fk0" FOREIGN KEY ("user_cabinet_id") REFERENCES "user_cabinet"("id");
ALTER TABLE "support" ADD CONSTRAINT "support_fk1" FOREIGN KEY ("user_account_id") REFERENCES "user_account"("id");

ALTER TABLE "transaction" ADD CONSTRAINT "transaction_fk0" FOREIGN KEY ("user_cabinet_id") REFERENCES "user_cabinet"("id");

