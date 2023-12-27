## ECS Cluster
resource "aws_ecs_cluster" "main" {
  name = var.ecs_cluster_name
}

## Task Definition
resource "aws_ecs_task_definition" "main" {
  family                   = var.ecs_task_family
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = 256
  memory                   = 512
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_role.arn
  container_definitions    = jsonencode([
    {
      name         = var.container_name
      image        = "evaluator:latest"
      essential    = true
      portMappings = [
        {
          protocol      = "tcp"
          containerPort = 8080
          hostPort      = 8080
        }
      ]
    }
  ])
}

