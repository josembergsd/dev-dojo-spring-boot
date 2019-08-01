package br.com.devdojo.springboot.error;

public class ValidationErrorDetails extends ErrorDetails {
	
	private String field;
	private String fieldMessage;
	
	//InnerClass
		public static final class Builder{
			private String title;
			private int status;
			private String details;
			private long timestamp;
			private String developerMessage;
			private String field;
			private String fieldMessage;
			private Builder() {
			}
			
			public static Builder newBuilder() {
				return new Builder();
			}
			
			public Builder title(String title) {
				this.title = title;
				return this;
			}
			
			public Builder status(int status) {
				this.status = status;
				return this;
			}
			
			public Builder details(String details) {
				this.details = details;
				return this;
			}
			
			public Builder timestamp(long timestamp) {
				this.timestamp = timestamp;
				return this;
			}
			
			public Builder developerMessage(String developerMessage) {
				this.developerMessage = developerMessage;
				return this;
			}
			
			public Builder field(String field) {
				this.field = field;
				return this;
			}
			
			public Builder fieldMessage(String fieldMessage) {
				this.fieldMessage = fieldMessage;
				return this;
			}
			
			public ValidationErrorDetails build() {
				ValidationErrorDetails validationErrorDetail = new ValidationErrorDetails();
				validationErrorDetail.setDeveloperMessage(developerMessage);
				validationErrorDetail.setTitle(title);
				validationErrorDetail.setDetails(details);
				validationErrorDetail.setTimestamp(timestamp);
				validationErrorDetail.setStatus(status);
				validationErrorDetail.setField(field);
				validationErrorDetail.setFieldMessage(fieldMessage);
				return validationErrorDetail;
			}
		}

		public String getField() {
			return field;
		}

		public String getFieldMessage() {
			return fieldMessage;
		}

		public void setField(String field) {
			this.field = field;
		}

		public void setFieldMessage(String fieldMessage) {
			this.fieldMessage = fieldMessage;
		}
		
}
