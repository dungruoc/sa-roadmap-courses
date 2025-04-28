{{- define "templating-deep-dive.fullName" -}}
{{- $defaultName := printf "%s-%s" .Release.Name .Chart.Name }}
{{- .Values.customName | default $defaultName | trunc 63 | trimSuffix "-" -}}
{{- end -}}

{{- define "templating-deep-dive.selectorLabels" -}}
app: {{ .Chart.Name }}
release: {{ .Release.Name }}
{{- end -}}

{{/* expecting port to be passed as the context . */}}
{{- define "templating-deep-dive.validators.portRange" -}}
{{- $sanitizedPort := int . -}}
{{- if or (lt $sanitizedPort 1) (gt $sanitizedPort 65535) -}}
{{- fail (printf "Invalid port %d. Ports must be between 1 and 65535" $sanitizedPort) -}}
{{- end -}}
{{- end -}}


{{/* expecting an object with port and type to be passed as the context . */}}
{{- define "templating-deep-dive.validators.service" -}}

{{- include "templating-deep-dive.validators.portRange" .port -}}

{{- $allowedSvcTypes := list "ClusterIP" "NodePort" -}}
{{- if not (has .type $allowedSvcTypes) -}}
{{- fail (printf "Invalid service type %s. Supported values are %s." .type $allowedSvcTypes) -}}
{{- end -}}
{{- end -}}