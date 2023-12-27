variable "ecr_repo_name" {
  description = "Name of the ECR repository"
  type        = string
  default     = "evaluator"
}

variable "ecs_cluster_name" {
  description = "Name of the ECS cluster"
  type        = string
  default     = "evaluator-cluster"
}

variable "ecs_task_family" {
  description = "Name of ECS task family"
  type        = string
  default     = "evaluator-task"
}

variable "container_name" {
  description = "Name of the ECS container"
  type        = string
  default     = "evaluator-container"
}

variable "iam_role_name" {
  description = "Name of the IAM ECS Task Role"
  type        = string
  default     = "java-ecsTaskRole"
}

variable "iam_task_execution_role_name" {
  description = "Name of ECS Task Execution Role"
  type        = string
  default     = "java-ecsTaskExecutionRole"
}

variable "ecs_service_name" {
  description = "Name of the ECS Service"
  type = string
  default = "evaluator-service"
}